package com.example.coffeshop.model.view;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeView {

    private long id;
    private String username;
    private int orders;
}
