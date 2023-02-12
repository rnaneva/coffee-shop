package com.example.coffeshop.service;

import com.example.coffeshop.model.entities.Category;
import com.example.coffeshop.model.entities.CategoryEnum;
import com.example.coffeshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategories(){
        if(categoryRepository.count() != 0){
            return;
        }

        List<Category> categories = Arrays.stream(CategoryEnum.values())
                .map(categoryE -> {
                    Category category = new Category();
                    category.setName(categoryE);

                    switch (categoryE) {
                        case CAKE -> category.setNeededTime(10);
                        case DRINK -> category.setNeededTime(1);
                        case COFFEE -> category.setNeededTime(2);
                        case OTHER -> category.setNeededTime(5);
                    }
                    return category;
                }).collect(Collectors.toList());

        categoryRepository.saveAll(categories);
    }

    public Category findByName(CategoryEnum name){
        return categoryRepository.findByName(name).orElse(null);
    }

}
