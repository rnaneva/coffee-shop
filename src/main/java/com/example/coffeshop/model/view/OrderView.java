package com.example.coffeshop.model.view;

import com.example.coffeshop.model.entities.Category;
import com.example.coffeshop.model.entities.User;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderView {

    private long id;

    private String name;

    private BigDecimal price;

    private Category category;


}
