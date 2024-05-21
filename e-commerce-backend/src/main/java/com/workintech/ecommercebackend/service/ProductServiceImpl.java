package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Product;
import com.workintech.ecommercebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
@Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }return null;//hata
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
