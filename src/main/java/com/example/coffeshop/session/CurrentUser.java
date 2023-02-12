package com.example.coffeshop.session;

import com.example.coffeshop.model.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@SessionScope
@Component
public class CurrentUser {

    private long id;

    public boolean isLogged(){
        return id > 0;
    }

    public void logIn(User user){
        this.id = user.getId();
    }

    public void logout(){
        this.id = 0;
    }
}
