package com.example.coffeshop.model.binding;

import com.example.coffeshop.model.entities.Category;
import com.example.coffeshop.model.entities.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderAddBindingModel {

    @NotNull
    @Size(min = 5)
    private String description;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    private LocalDateTime orderTime;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private CategoryEnum category;

}
