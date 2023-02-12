package com.example.coffeshop.validation;

import com.example.coffeshop.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserService userService;

    public EmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByEmail(s) == null;
    }
}
