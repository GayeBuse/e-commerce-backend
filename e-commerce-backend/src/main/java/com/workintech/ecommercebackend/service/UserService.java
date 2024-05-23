package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.User;

import java.util.List;
//UserService: Kullanıcı kayıtlarıyla ilgili temel veritabanı işlemlerini içerir. Kullanıcı bilgilerini getirir, yeni kullanıcı oluşturur, mevcut kullanıcıyı günceller veya siler.
public interface UserService {
    User findById(Long id);
    List<User> findAll();
    User save(User user);
    User update(Long id,User updatedUser);
    User delete(Long id);
}
