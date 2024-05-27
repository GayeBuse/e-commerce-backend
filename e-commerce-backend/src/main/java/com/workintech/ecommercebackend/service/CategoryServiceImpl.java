package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Category;
import com.workintech.ecommercebackend.exceptions.GlobalExceptions;
import com.workintech.ecommercebackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory= categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            return optionalCategory.get();
        } throw new GlobalExceptions("No category found with this id: " + id, HttpStatus.NOT_FOUND);
    }


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
