package com.example.coffeshop.service;

import com.example.coffeshop.model.view.EmployeeView;
import com.example.coffeshop.model.view.OrderView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

    private UserService userService;
    private OrderService orderService;


    public HomeService(UserService userService,
                       OrderService orderService
                      ) {
        this.userService = userService;
        this.orderService = orderService;

    }

    public List<OrderView> allOrders() {
        return orderService.findAllBy_OrderByPrice().
                stream().map(order -> OrderView.builder()
                         .name(order.getName())
                         .id(order.getId())
                         .price(order.getPrice())
                         .category(order.getCategory())
                         .build())
                .collect(Collectors.toList());
    }

    public List<EmployeeView> allEmployees() {
        return userService.findAllBy_OrderByOrders().stream()
                .map(user -> EmployeeView.builder()
                         .username(user.getUsername())
                         .orders(user.getOrders().size())
                         .build())
                .collect(Collectors.toList());
    }

    public int totalTime(){
        return allOrders().stream()
                .map(orderView -> orderView.getCategory().getNeededTime())
                .reduce(Integer::sum).orElse(0);
    }
}
