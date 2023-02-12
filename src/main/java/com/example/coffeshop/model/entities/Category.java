package com.example.coffeshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    @Column(nullable = false)
    private int neededTime;

}
