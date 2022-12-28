import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserOrder } from 'src/app/models/UserOrder';
import { UserOrderService } from 'src/app/service/user-order.service';

@Component({
  selector: 'app-user-order',
  templateUrl: './user-order.component.html',
  styleUrls: ['./user-order.component.css']
})
export class UserOrderComponent implements OnInit {

  public orders: Array<UserOrder> = [];
  public selectedOrder: UserOrder | undefined;
  public ok = true;
  public username = ""


  ngOnInit(): void {
    this.username = sessionStorage.getItem('username')!;
    this.getOrdersByUserName();
  }

  constructor(private ordersService: UserOrderService, private router: Router, private activatedRoute: ActivatedRoute) {
    //this.getOrdersByUserName();
    
  }


  onSelect(order: UserOrder): void {
    this.selectedOrder = order;
  }

  getOrdersByUserName(): void {
    this.ordersService.getOrdersByUserName(this.username)
      .subscribe(response => {
        this.orders = response.userOrders;
        console.log(this.orders);
      });
  }

}
