import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AddOrderDTO } from 'src/app/dto/userOrder/AddOrderDTO';
import { Product } from 'src/app/models/Product';
import { UserOrder } from 'src/app/models/UserOrder';
import { ProductService } from 'src/app/service/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  public products: Array<Product> = [];
  public partialOrders: Array<UserOrder> = [];
  public selectedProduct: Product | undefined;
  public ok = true;
  public quantity = 0;
  public userName = "";


  ngOnInit(): void {
    this.getProducts();
    this.userName = localStorage.getItem('username')!;
  }

  constructor(private productsService: ProductService, private router: Router, private activatedRoute:ActivatedRoute) {
  }

  onSelect(product: Product): void {
    this.selectedProduct = product;
  }

  getProducts(): void {
    this.productsService.getProducts()
      .subscribe(response => {
        this.products = response.products;
        console.log(this.products);
      });
  }

  public addToCart(quantity: number): void{
    this.partialOrders.push({id: 0, userName: this.userName, productId: this.selectedProduct?.id || 0, quantity: quantity} as AddOrderDTO);
  }
  
  public searchProduc(search: string): void{
    
  }
}
