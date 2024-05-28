package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//UserDetailsServicee sınıfı,
// Spring Security'nin bir parçasıdır
// ve kullanıcı kimlik doğrulama
// işlemlerini gerçekleştirir.
// Bu sınıf, kullanıcı bilgilerini
// yüklemek için bir servis sağlar ve
// özellikle kullanıcı adı (veya e-posta)
// temelinde kullanıcıları bulur.
//Bu sınıf olmadan, Spring Security
// ile kullanıcı kimlik doğrulaması
// yapılamaz. Kimlik doğrulama işlemleri,
// kullanıcı bilgilerini yükleyemez ve
// güvenlik kontrolleri çalışmaz.
@Service
public class UserDetailsServicee implements UserDetailsService {
    private UserRepository userRepository;
@Autowired
    public UserDetailsServicee(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {//Bu metot, verilen bir kullanıcı adına göre bir UserDetails nesnesi yükler.
       // Kullanıcı bulunamazsa, UsernameNotFoundException fırlatılabilir.
              //  UserDetails nesnesi, kullanıcı hakkındaki bilgileri içerir. Bu bilgiler genellikle kullanıcı adı, şifre ve roller gibi temel bilgileri içerir.
        return userRepository.findUserByEmail(email) //Kullanıcıyı e-posta adresine göre bulmaya çalışır.
                .orElseThrow(()->{ //// Eğer kullanıcı bulunamazsa
                    System.out.println("user are not valid");
                    throw new UsernameNotFoundException("user are not valid");
                });
    }
}
