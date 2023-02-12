package com.example.coffeshop.web;

import com.example.coffeshop.model.binding.UserLoginBindingModel;
import com.example.coffeshop.model.binding.UserRegisterBindingModel;
import com.example.coffeshop.service.UserService;
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
@RequestMapping("/users")
public class AuthController {

    private UserService userService;
    private CurrentUser currentUser;

    public AuthController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String register() {

        if(currentUser.isLogged()){
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {


        if(currentUser.isLogged()){
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !userService.registerUser(userRegisterBindingModel)) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);

            return "redirect:register";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(){

        if(currentUser.isLogged()){
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping("/login")
    public String goLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if(currentUser.isLogged()){
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !userService.loginUser(userLoginBindingModel)){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);
            return "redirect:login";
        }


        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout(){

        if(currentUser.isLogged()){
            currentUser.logout();
        }
        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }


}
