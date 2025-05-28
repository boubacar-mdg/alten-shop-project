
import { Injectable, inject, signal, computed } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, of, tap } from "rxjs";
import { CartItem } from "./cart-item.model";
import { Product } from "app/products/data-access/product.model";


@Injectable({
    providedIn: "root"
})
export class CartService {

    private readonly http = inject(HttpClient);
    private readonly path = "/api/cart";
    
    private readonly _cartItems = signal<CartItem[]>([]);

    public readonly cartItems = this._cartItems.asReadonly();

    public isCartDialogVisible = false;

    public readonly totalItems = computed(() => 
        this._cartItems().reduce((total, item) => total + item.quantity, 0)
    );
    
    public readonly totalPrice = computed(() => 
        this._cartItems().reduce((total, item) => total + (item.product.price * item.quantity), 0)
    );
    
    public readonly isEmpty = computed(() => this._cartItems().length === 0);

    public get(): Observable<CartItem[]> {
        return this.http.get<CartItem[]>(this.path).pipe(
            catchError((error) => {
                const savedCart = localStorage.getItem('cart');
                return of(savedCart ? JSON.parse(savedCart) : []);
            }),
            tap((cartItems) => {
                this._cartItems.set(cartItems);
                this.saveToLocalStorage(cartItems);
            }),
        );
    }

    public addItem(product: Product, quantity: number = 1): Observable<boolean> {
        const cartItem: CartItem = {
            id: Date.now(),
            product,
            quantity,
            addedAt: new Date()
        };

        return this.http.post<boolean>(`${this.path}/add`, cartItem).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => {
                this._cartItems.update(items => {
                    const existingItemIndex = items.findIndex(item => item.product.id === product.id);
                    
                    if (existingItemIndex >= 0) {
                        return items.map((item, index) => 
                            index === existingItemIndex 
                                ? { ...item, quantity: item.quantity + quantity }
                                : item
                        );
                    } else {
                        return [cartItem, ...items];
                    }
                });
                this.saveToLocalStorage(this._cartItems());
            }),
        );
    }

    public updateQuantity(cartItemId: number, quantity: number): Observable<boolean> {
        if (quantity <= 0) {
            return this.removeItem(cartItemId);
        }

        return this.http.patch<boolean>(`${this.path}/${cartItemId}`, { quantity }).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => {
                this._cartItems.update(items => {
                    return items.map(item => 
                        item.id === cartItemId 
                            ? { ...item, quantity }
                            : item
                    );
                });
                this.saveToLocalStorage(this._cartItems());
            }),
        );
    }

    public removeItem(cartItemId: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.path}/${cartItemId}`).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => {
                this._cartItems.update(items => 
                    items.filter(item => item.id !== cartItemId)
                );
                this.saveToLocalStorage(this._cartItems());
            }),
        );
    }

    private saveToLocalStorage(cartItems: CartItem[]): void {
        try {
            localStorage.setItem('cart', JSON.stringify(cartItems));
        } catch (error) {
            console.warn('Failed to save cart to localStorage:', error);
        }
    }

  }