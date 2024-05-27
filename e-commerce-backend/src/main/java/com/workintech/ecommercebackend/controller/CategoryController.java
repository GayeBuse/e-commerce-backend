package com.workintech.ecommercebackend.controller;

import com.workintech.ecommercebackend.dto.CategoryResponse;
import com.workintech.ecommercebackend.entity.Category;
import com.workintech.ecommercebackend.exceptions.GlobalExceptions;
import com.workintech.ecommercebackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/category")
public class CategoryController {


    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        List<Category> categoryList = categoryService.findAllCategories();//Kategori Listesini Almak:
        List<CategoryResponse> newList = new ArrayList<>();//Yeni Bir Liste Oluşturmak
        categoryList.forEach(category -> {
            newList.add(new CategoryResponse(category.getId(), category.getTitle(), category.getCode(), category.getImage(), category.getRating()));//Kategorileri Dönüştürmek ve Yeni Listeye Eklemek:
        });
        return newList;
        //Yeni Listeyi Döndürmek:
    }
    

    @PostMapping
    public Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody Category category) {
        Category newCategory = categoryService.findById(id);
        if (newCategory != null) {
            categoryService.save(category);
        } else {
            throw new GlobalExceptions("Category is not found with this id: " + id, HttpStatus.NOT_FOUND);
        }
        return new CategoryResponse(category.getId(), category.getTitle(), category.getCode(), category.getImage(), category.getRating());
    }


}
