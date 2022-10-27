package service;

import dtos.order.AddOrderDTO;
import entities.Order;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(AddOrderDTO dto){
        this.orderRepository.save(new Order(dto));
    }

    public void saveOrder(List<Order> partialOrders){
        this.orderRepository.saveAll(partialOrders);
    }
}
