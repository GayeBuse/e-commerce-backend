package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    List<User> findAll();
    User save(User user);
    User update(Long id,User updatedUser);
    User delete(Long id);
}
