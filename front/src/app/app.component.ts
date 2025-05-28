import {
  Component,
  inject,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { CartService } from "./cart/data-access/cart.service";
import { DialogModule } from "primeng/dialog";
import { CartItemListComponent } from "./cart/ui/cart-item-list/cart-item-list.component";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent,  DialogModule, CartItemListComponent],
})
export class AppComponent {
  title = "ALTEN SHOP";

  protected readonly cartService = inject(CartService);
  public isCartDialogVisible = false;

  ngOnInit() {
    this.cartService.get().subscribe();
  }

  public openCart(){
    this.isCartDialogVisible = true;
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isCartDialogVisible = false;
  }

}
