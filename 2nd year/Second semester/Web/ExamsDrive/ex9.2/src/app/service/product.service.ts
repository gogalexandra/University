import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';
import { AddProductDTO } from '../dto/product/AddProductDTO';
import { Product } from '../models/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private productsURL = 'http://localhost:8080/api/products';

  constructor(private httpClient: HttpClient) {
  }

  public getProducts(): Observable<any> {
    return this.httpClient.get<Array<Product>>(this.productsURL).pipe(
      catchError(this.handleError<Product[]>('getProducts', []))
    );
  }

  public add(product: AddProductDTO): Observable<any> {
    return this.httpClient.post<any>(this.productsURL, {name: product.name, description: product.description} as AddProductDTO).pipe(
      catchError(this.handleError<any>('addProduct'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }
}
