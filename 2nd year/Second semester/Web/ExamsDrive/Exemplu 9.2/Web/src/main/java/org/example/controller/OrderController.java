package org.example.controller;

import dto.Order.AddOrdersDTO;
import dto.Order.GetOrdersDTO;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{userName}")
    GetOrdersDTO getOrdersByUserName(@PathVariable String userName) {
        return new GetOrdersDTO(orderService.getOrdersByName(userName));
    }

    @GetMapping("/orders")
    GetOrdersDTO all() {
        return new GetOrdersDTO(orderService.getOrders());
    }

    @PostMapping("/orders")
    void add(@RequestBody AddOrdersDTO dto) {
        orderService.addOrders(dto);
    }
}
