import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddproductComponent } from './components/add-product/add-product.component';
import { PartialOrderComponent } from './components/partial-order/partial-order.component';
import { ProductComponent } from './components/product/product.component';
import { UserOrderComponent } from './components/user-order/user-order.component';

const routes: Routes = [
  {path: 'products', component: ProductComponent},
  {path: 'add-product', component: AddproductComponent},
  {path: 'orders', component: UserOrderComponent},
  {path: 'cart', component: PartialOrderComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
