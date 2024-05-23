package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Role;
import com.workintech.ecommercebackend.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface AuthService {
    User register(String fullName, String email, String password);
    User login(String email, String password);
    void logout(HttpServletRequest request, HttpServletResponse response);
}
