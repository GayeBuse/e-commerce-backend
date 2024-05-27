package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category findById(Long id);
    Category save(Category category);


}
