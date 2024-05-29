package com.workintech.ecommercebackend.controller;

import com.workintech.ecommercebackend.dto.UserAndAddressResponse;
import com.workintech.ecommercebackend.dto.UserResponse;
import com.workintech.ecommercebackend.entity.Address;
import com.workintech.ecommercebackend.entity.User;
import com.workintech.ecommercebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
@Autowired

    public UserController(UserService userService) {

    this.userService = userService;
    }
    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public UserAndAddressResponse findById(@PathVariable long id){
        User user = userService.findById(id);
        List<Address> addressList = new ArrayList<>();
        user.getAddresses().forEach(address -> {
            addressList.add(address);
        });
        return new UserAndAddressResponse(user.getId(), user.getName(), user.getEmail(), user.getAddresses());
    }

    @PutMapping("/{id}")
    public UserResponse userUpdate(@PathVariable Long id, @RequestBody User userUpdate) {
        User updatedUser = userService.update(id, userUpdate);
        return new UserResponse(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
    }


    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable Long id){
        User user = userService.findById(id);
        userService.delete(id);
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }


}
