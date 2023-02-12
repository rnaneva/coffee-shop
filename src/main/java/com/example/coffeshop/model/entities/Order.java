package com.example.coffeshop.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime orderTime;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User employee;
}
