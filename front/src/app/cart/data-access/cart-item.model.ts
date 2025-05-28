import { Product } from "app/products/data-access/product.model";

export interface CartItem {
    id: number;
    product: Product;
    quantity: number;
    addedAt: Date;
}