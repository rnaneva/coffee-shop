package com.example.coffeshop.repository;

import com.example.coffeshop.model.entities.Category;
import com.example.coffeshop.model.entities.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryEnum name);
}
