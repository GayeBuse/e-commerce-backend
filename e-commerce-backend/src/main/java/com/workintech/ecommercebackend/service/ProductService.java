package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);

}
