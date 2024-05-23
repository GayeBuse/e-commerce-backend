package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Role;
import com.workintech.ecommercebackend.entity.User;
import com.workintech.ecommercebackend.repository.RoleRepository;
import com.workintech.ecommercebackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Kullanıcı kaydı (register).
//Kullanıcı girişi (login).
//Kullanıcı çıkışı (logout).
@Service
public class AuthenticationService implements AuthService {

private UserRepository userRepository;
private RoleRepository roleRepository;
//PasswordEncoder, Spring Security framework'ünde şifrelerin güvenli bir şekilde yönetilmesi için kullanılan bir arayüzdür.
private PasswordEncoder passwordEncoder;
@Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
// Bu metot, bir kullanıcının tam adı, e-posta adresi ve şifresi gibi bilgileri alır ve bu bilgileri kullanarak yeni bir kullanıcı kaydı oluşturur.
    @Override
    public User register(String fullName, String email, String password) {
        Optional<User> existingUser = userRepository.findUserByEmail(email);
        if (existingUser.isPresent()) {
            return null; //mevcut kullanıcı konntrolü
        }
        String encodedPassword = passwordEncoder.encode(password); //güvenlik amacıyla

        Role role = new Role();
        role.setAuthority("USER");
        Role userRole = roleRepository.save(role); //Yeni bir Role nesnesi oluşturulur ve bu rolün yetkisi USER olarak belirlenir.
        //Bu rol, roleRepository.save(role) ile veritabanına kaydedilir ve userRole değişkenine atanır.
//Yeni bir User nesnesi oluşturulur.
//Kullanıcının adı, e-posta adresi, şifrelenmiş şifresi ve yetkileri (rolleri) atanır.
        User user = new User();
        user.setName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(List.of(userRole));
       // Kullanıcının Veritabanına Kaydedilmesi:
        return userRepository.save(user);

    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) { //parametreleri, mevcut HTTP isteği ve yanıtı temsil eder.
    // Bu, kullanıcı oturumu ve oturum verileri ile ilgili işlemler için gereklidir.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //SecurityContextHolder, mevcut güvenlik bağlamını
        // (security context) tutar ve yönetir.
        if (auth != null) { //Eğer auth nesnesi null değilse, yani bir kullanıcı oturum açmışsa, logout işlemi yapılır.
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
}
