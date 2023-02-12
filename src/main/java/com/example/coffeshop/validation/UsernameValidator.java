package com.example.coffeshop.validation;

import com.example.coffeshop.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private UserService userService;

    public UsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByUsername(s) == null;
    }
}
