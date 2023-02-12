package com.example.coffeshop.web;

import com.example.coffeshop.model.binding.OrderAddBindingModel;
import com.example.coffeshop.service.OrderService;
import com.example.coffeshop.session.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;
    private CurrentUser currentUser;

    public OrderController(OrderService orderService, CurrentUser currentUser) {
        this.orderService = orderService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String addOrder(){
        return "order-add";
    }

    @PostMapping("/add")
    public String doAddOrder(@Valid OrderAddBindingModel orderAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if(!currentUser.isLogged()){
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !orderService.addOrder(orderAddBindingModel)){
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }

        return "redirect:/";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel(){
        return new OrderAddBindingModel();
    }
}
