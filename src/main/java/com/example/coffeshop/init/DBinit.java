package com.example.coffeshop.init;

import com.example.coffeshop.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBinit implements CommandLineRunner {

    private CategoryService categoryService;

    public DBinit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        categoryService.addCategories();

    }
}
