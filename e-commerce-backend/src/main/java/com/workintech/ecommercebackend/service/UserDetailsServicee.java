package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServicee implements UserDetailsService {
    private UserRepository userRepository;
@Autowired
    public UserDetailsServicee(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email) //Kullanıcıyı e-posta adresine göre bulmaya çalışır.
                .orElseThrow(()->{ //// Eğer kullanıcı bulunamazsa
                    System.out.println("user are not valid");
                   return null; // hata yaz
                });
    }
}
