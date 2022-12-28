import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AddProductDTO } from 'src/app/dto/product/AddProductDTO';
import { Product } from 'src/app/models/Product';
import { ProductService } from 'src/app/service/product.service';

@Component({
  selector: 'app-addproduct',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddproductComponent implements OnInit {

  public products: Array<Product> = [];
  public selectedProduct: Product | undefined;
  public ok = true;
  public username = "";


  ngOnInit(): void {
    this.username = sessionStorage.getItem('username')!;
    this.getProducts();
  }

  constructor(private productsService: ProductService, private router: Router, private activatedRoute: ActivatedRoute) {
  }


  addProductForm = new FormGroup({
    productName: new FormControl('', Validators.required),
    productDescription: new FormControl('', Validators.required),
  })

  addProductFormMethod(data: { name: string; description: string; }) {
    let name = data.name.trim();
    let description = data.description.trim();
    if (!name || !description) {
      alert("Please fill all fields");
      return;
    }
    this.productsService.add({
      name: name,
      description: description,
    } as AddProductDTO)
      .subscribe(
        _ => this.getProducts()
      )
  }

  onSubmit() {
    this.addProductFormMethod(this.addProductForm.value)
  }

  onSelect(product: Product): void {
    this.selectedProduct = product;
  }


  add(productName: string, productDescription: string): void {
    let name = productName.trim();
    let description = productDescription.trim();
    if (!name || !description) {
      alert("Please fill all fields");
      return;
    }
    this.productsService.add({
      name: name,
      description: description,
    } as AddProductDTO)
      .subscribe(
        _ => this.getProducts()
      )
  }

  getProducts(): void {
    this.productsService.getProducts()
      .subscribe(response => {
        this.products = response.products;
        console.log(this.products);
      });
  }

}
