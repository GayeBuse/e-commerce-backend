package com.workintech.ecommercebackend.dto;

import com.workintech.ecommercebackend.entity.Order;
import com.workintech.ecommercebackend.entity.Product;
import com.workintech.ecommercebackend.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
        private List<Product> productList;
        private Order order;
        private User user;
    }

