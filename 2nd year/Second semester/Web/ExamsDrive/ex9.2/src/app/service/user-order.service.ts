import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { AddOrderDTO } from '../dto/userOrder/AddOrderDTO';
import { UserOrder } from '../models/UserOrder';

@Injectable({
  providedIn: 'root'
})
export class UserOrderService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private ordersURL = 'http://localhost:8080/api/orders';

  constructor(private httpClient: HttpClient) {
  }

  public getOrdersByUserName(userName: string): Observable<any> {
    const url = `${this.ordersURL}/${userName}`;
    return this.httpClient.get<Array<UserOrder>>(url).pipe(
      catchError(this.handleError<UserOrder[]>('getOrders', []))
    );
  }

  public add(order: AddOrderDTO): Observable<any> {
    return this.httpClient.post<any>(this.ordersURL, {userName: order.userName, productId: order.productId, quantity: order.quantity} as AddOrderDTO).pipe(
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
