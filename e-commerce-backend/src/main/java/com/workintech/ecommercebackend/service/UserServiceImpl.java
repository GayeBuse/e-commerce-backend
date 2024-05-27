package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.User;
import com.workintech.ecommercebackend.exceptions.GlobalExceptions;
import com.workintech.ecommercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();

        }
        throw new GlobalExceptions("User is not found with this id: " + id, HttpStatus.NOT_FOUND);
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new GlobalExceptions("This email is already in use", HttpStatus.CONFLICT);

        }
        return userRepository.save(user);
    }


    @Override
    public User update(Long id, User updatedUser) {
        throw new GlobalExceptions("User is not found with this id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public User delete(Long id) {
        User user = findById(id);
        if (user != null) {
            userRepository.delete(user);
            return user;
        }
        throw new GlobalExceptions("User is not found with this id: " + id, HttpStatus.NOT_FOUND);
    }
}
