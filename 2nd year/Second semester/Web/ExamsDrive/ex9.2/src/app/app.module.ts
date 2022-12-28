import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { ProductComponent } from './components/product/product.component';
import { UserOrderComponent } from './components/user-order/user-order.component';
import { AddproductComponent } from './components/add-product/add-product.component';
import { PartialOrderComponent } from './components/partial-order/partial-order.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    UserOrderComponent,
    AddproductComponent,
    PartialOrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
