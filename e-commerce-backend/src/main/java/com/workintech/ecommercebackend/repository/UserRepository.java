package com.workintech.ecommercebackend.repository;

import com.workintech.ecommercebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);
}
//Optional kullanımının nedeni, bu tür özel sorguların null
// döndürme olasılığına karşı daha güvenli bir yaklaşım sunmasıdır.
// Eğer belirtilen kriterlere uygun bir kayıt bulunamazsa,
// Optional'ın içi boş olur ve bu durumda null pointer exception
// gibi hatalarla başa çıkmak daha kolay hale gelir. Bu nedenle,
// Spring Data JPA'da genellikle tek bir kayıt döndürme beklendiğinde
// Optional kullanımı tercih edilir.