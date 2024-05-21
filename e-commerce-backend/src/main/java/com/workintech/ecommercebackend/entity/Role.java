package com.workintech.ecommercebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

// Spring Security çerçevesinde yetki tanımlaması için kullanılan arayüz.
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="role",schema="e-commerce")
public class Role implements GrantedAuthority { //Spring Security'nin GrantedAuthority arayüzünü uygular. Bu, rolün güvenlik yetkisi olarak kullanılabileceği anlamına gelir.
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id;
@Column(name = "authority")
private String authority;

// (yetki) alanına sahiptir. Spring Security'nin GrantedAuthority arayüzünü uygulayarak, bu sınıf yetkileri temsil eder ve güvenlik kontrollerinde kullanılır.




}
