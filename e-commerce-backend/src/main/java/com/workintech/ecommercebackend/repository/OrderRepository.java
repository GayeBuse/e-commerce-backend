package com.workintech.ecommercebackend.repository;

import com.workintech.ecommercebackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
