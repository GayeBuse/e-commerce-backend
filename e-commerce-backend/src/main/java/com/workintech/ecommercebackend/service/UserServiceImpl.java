package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.User;
import com.workintech.ecommercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<User> optionalUser=userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        } return null;//hata
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return null; // hata yaz

        }
        return userRepository.save(user);
    }


    @Override
    public User update(Long id, User updatedUser) {
        return null;
    }

    @Override
    public User delete(Long id) {
        User user = findById(id);
        if (user != null) {
            userRepository.delete(user);
            return user;
        }
        return null; // hata
    }
}
