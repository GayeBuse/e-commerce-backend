package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.dto.OrderResponse;
import com.workintech.ecommercebackend.entity.Order;

import java.util.List;

public interface OrderService {


    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
    Order delete(Long id);



}
