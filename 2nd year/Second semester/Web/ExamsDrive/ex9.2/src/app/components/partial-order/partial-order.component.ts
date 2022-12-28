import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AddOrderDTO } from 'src/app/dto/userOrder/AddOrderDTO';
import { UserOrder } from 'src/app/models/UserOrder';
import { UserOrderService } from 'src/app/service/user-order.service';

@Component({
  selector: 'app-partial-order',
  templateUrl: './partial-order.component.html',
  styleUrls: ['./partial-order.component.css']
})
export class PartialOrderComponent implements OnInit {

  public partialOrders: Array<UserOrder> = [];
  public username = "";

  constructor(private router: ActivatedRoute, private ordersService: UserOrderService) { }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('username')!;

    this.router.queryParams.subscribe(p => {
      this.partialOrders = p['partialOrders'];
  });
  }

  public saveCart(): void{
    this.partialOrders.forEach(po => {this.ordersService.add({
      userName: po.userName,
      productId: po.productId,
      quantity: po.quantity,
    } as AddOrderDTO)});
  }


  public emptyCart(): void{
    this.partialOrders = [];
  }

}
