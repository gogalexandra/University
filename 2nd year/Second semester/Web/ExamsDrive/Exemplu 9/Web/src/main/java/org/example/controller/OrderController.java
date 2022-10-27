package org.example.controller;

import dtos.order.AddOrderDTO;
import dtos.order.GetOrdersDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.OrderService;

@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orders")
    GetOrdersDTO all() {
        return new GetOrdersDTO(orderService.getOrders());
    }

    @PostMapping("/orders")
    void add(@RequestBody AddOrderDTO dto) {
        orderService.addOrder(dto);
    }
}
