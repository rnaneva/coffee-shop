package com.example.coffeshop.service;

import com.example.coffeshop.model.binding.OrderAddBindingModel;
import com.example.coffeshop.model.entities.Category;
import com.example.coffeshop.model.entities.Order;
import com.example.coffeshop.model.entities.User;
import com.example.coffeshop.repository.OrderRepository;
import com.example.coffeshop.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ModelMapper modelMapper;
    private CurrentUser currentUser;
    private UserService userService;
    private CategoryService categoryService;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService,
                       CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;

    }

    public boolean addOrder(OrderAddBindingModel orderAddBindingModel){

        Order order = modelMapper.map(orderAddBindingModel, Order.class);
        User user = userService.findById(currentUser.getId());
        Category category = categoryService.findByName(orderAddBindingModel.getCategory());

        if(user == null || category == null){
            return false;
        }

        order.setCategory(category);
        order.setEmployee(user);
        orderRepository.save(order);

        return true;
    }

    List<Order> findAllBy_OrderByPrice(){
        return orderRepository.findAllByOrderByPriceDesc();
    }


    public void deleteOrder(long id){
        orderRepository.findById(id).ifPresent(order -> orderRepository.delete(order));
    }

}
