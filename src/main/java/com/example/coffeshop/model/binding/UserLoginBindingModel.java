package com.example.coffeshop.model.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginBindingModel {

    @NotNull
    @Size(min = 5, max = 20)
    private String username;

    @NotNull
    @Size(min = 3)
    private String password;
}
