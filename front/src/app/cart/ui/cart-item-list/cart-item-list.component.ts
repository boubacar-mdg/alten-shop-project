import { Component, inject } from '@angular/core';
import { CartItem } from 'app/cart/data-access/cart-item.model';
import { CartService } from 'app/cart/data-access/cart.service';
import { Product } from 'app/products/data-access/product.model';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DataViewModule } from 'primeng/dataview';

@Component({
  selector: 'app-cart-item-list',
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule],
  templateUrl: './cart-item-list.component.html',
  styleUrl: './cart-item-list.component.css'
})
export class CartItemListComponent {

  protected readonly cartService = inject(CartService);

  public readonly cartItems = this.cartService.cartItems;

  public onDeleteItem(cartItemId: number) {
    this.cartService.removeItem(cartItemId).subscribe();
  }
  
  public onAddItemToCart(product: Product) {
    this.cartService.addItem(product).subscribe();
  }

  public decreaseItemCount(cartItem: CartItem) {
    this.cartService.updateQuantity(cartItem.id, cartItem.quantity - 1).subscribe();
  }

}
