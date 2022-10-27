package service;

import dto.Order.AddOrdersDTO;
import entities.UserOrder;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

import java.util.List;


@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<UserOrder> getOrders(){return orderRepository.findAll();}

    public List<UserOrder> getOrdersByName(String userName){return orderRepository.findAllByUserName(userName);}


    public void addOrders(AddOrdersDTO dto) {orderRepository.save(new UserOrder(dto));}
}
