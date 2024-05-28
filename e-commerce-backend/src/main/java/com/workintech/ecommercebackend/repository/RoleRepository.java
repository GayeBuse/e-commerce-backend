package com.workintech.ecommercebackend.repository;

import com.workintech.ecommercebackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role r WHERE r.authority = :authority")
    Optional<Role> findByAuthority(String authority);
}
//findByAuthority metodu, Role entity'sindeki authority alanını
// kullanarak belirli bir role erişim sağlar. Bu metot, Optional<Role>
// türünde bir nesne döndürür. Eğer belirtilen authority değerine sahip bir
// rol bulunursa, bu rolün bir Optional içinde döndürülmesi beklenir.
// Eğer böyle bir rol bulunmazsa, Optional'ın içi boş olur.