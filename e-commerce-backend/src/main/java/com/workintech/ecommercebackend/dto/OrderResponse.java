package com.workintech.ecommercebackend.dto;

import com.workintech.ecommercebackend.entity.Address;
import com.workintech.ecommercebackend.entity.Product;
import com.workintech.ecommercebackend.entity.User;

import java.time.LocalDate;
import java.util.List;

public record OrderResponse(Long id, List<Product> productList, User user, Address address ) {
}
