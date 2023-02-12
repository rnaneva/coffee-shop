package com.example.coffeshop.model.binding;

import com.example.coffeshop.validation.UniqueEmail;
import com.example.coffeshop.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterBindingModel {

    @NotNull
    @Email
    @UniqueEmail
    private String email;

    private String firstName;

    @NotNull
    @Size(min = 5, max = 20)
    private String lastName;

    @NotNull
    @Size(min = 5, max = 20)
    @UniqueUsername
    private String username;

    @NotNull
    @Size(min = 3)
    private String password;

    @NotNull
    @Size(min = 3)
    private String confirmPassword;


}
