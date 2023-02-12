package com.example.coffeshop.web;

import com.example.coffeshop.model.entities.Order;
import com.example.coffeshop.model.view.EmployeeView;
import com.example.coffeshop.model.view.OrderView;
import com.example.coffeshop.repository.OrderRepository;
import com.example.coffeshop.service.HomeService;
import com.example.coffeshop.service.OrderService;
import com.example.coffeshop.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private CurrentUser currentUser;
    private HomeService homeService;
    private OrderService orderService;


    public HomeController(CurrentUser currentUser, HomeService homeService,
                          OrderService orderService
                          ) {
        this.currentUser = currentUser;
        this.homeService = homeService;
        this.orderService = orderService;

    }

    @GetMapping("/")
    public String home(Model model) {
        if (!currentUser.isLogged()) {
            return "index";
        }

        List<OrderView> orders = homeService.allOrders();
        List<EmployeeView> employees = homeService.allEmployees();
        int totalTime = homeService.totalTime();

        model.addAttribute("orders", orders);
        model.addAttribute("employees", employees);
        model.addAttribute("totalTime", totalTime);

        return "home";
    }

    @GetMapping("/orders/ready/{id}")
    public String ready(@PathVariable long id) {

        orderService.deleteOrder(id);

        return "redirect:/";
    }


}
