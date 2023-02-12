package com.example.coffeshop.service;

import com.example.coffeshop.model.binding.UserLoginBindingModel;
import com.example.coffeshop.model.binding.UserRegisterBindingModel;
import com.example.coffeshop.model.entities.User;
import com.example.coffeshop.model.view.EmployeeView;
import com.example.coffeshop.repository.UserRepository;
import com.example.coffeshop.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private CurrentUser currentUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User findByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }


    public boolean registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return false;
        }

        User user = modelMapper.map(userRegisterBindingModel, User.class);
        userRepository.save(user);

        return true;
    }

    public boolean loginUser(UserLoginBindingModel userLoginBindingModel){

        Optional<User> optUser =
                userRepository.findByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if(optUser.isEmpty()){
            return false;
        }

        currentUser.logIn(optUser.get());

        return true;
    }

    public User findById(long id){
        return userRepository.findById(id).orElse(null);
    }

    List<User> findAllBy_OrderByOrders(){
        return userRepository.findAllByOrderByOrdersDesc();
    }

}
