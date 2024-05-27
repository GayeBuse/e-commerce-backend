package com.workintech.ecommercebackend.controller;

import com.workintech.ecommercebackend.dto.RegisterUser;
import com.workintech.ecommercebackend.entity.User;
import com.workintech.ecommercebackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
//gelen isteklerin veya giriş verilerinin belirli kurallara uygun olup olmadığını kontrol etmek için kullanılır.
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;
   @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public User register(@Validated @RequestBody RegisterUser registerUser){
        return authenticationService.register(registerUser.fullName(), registerUser.email(),
                registerUser.password());
    }

}
//Bu denetleyici, /auth/register yoluyla gelen POST isteklerini işleyerek, yeni bir kullanıcı kaydı oluşturur. Gelen JSON verisi, RegisterUser nesnesine dönüştürülür ve bu veriler kullanılarak AuthenticationService yardımıyla yeni bir User kaydedilir. Bu tür bir yapı, kullanıcı kaydı gibi işlemleri gerçekleştirmek için tipik bir Spring Boot uygulaması yaklaşımıdır.