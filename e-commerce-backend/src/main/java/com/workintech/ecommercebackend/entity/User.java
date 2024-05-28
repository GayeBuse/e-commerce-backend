package com.workintech.ecommercebackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user",schema = "e-commerce")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "It is mandatory to fill in the name field")//anotasyonu, bir alanın null olmamasını, boş bir string ("") olmamasını ve yalnızca beyaz boşluk karakterlerinden (" ") oluşmamasını sağlar.
    @Size(min = 3,max = 35,message = "You must enter at least 3 characters and greater than 35 characters.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Must be a valid email address.")
    @Size(min = 8,max = 100,message = "Password must not be less than 8 and greater than 100 characters.")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "Password must be at least 8 characters long.")
    @Size(min = 8,max = 100,message = "Password must not be less than 8 and greater than 100 characters.")
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) // Roller, kullanıcı sorgulandığında hemen getirilir. Bu, rollerin veri tabanından hemen yüklenmesini sağlar.
    @JoinTable(name = "user_role", schema = "e-commerce",
            joinColumns = @JoinColumn(name = "user_id"), //birbirlerinin primery keylerine denk gelen nameler
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> authorities = new ArrayList<>(); // Bu, kullanıcının rollerinin saklanacağı bir liste tanımlar.


    @ManyToMany (cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "address_user",schema = "e-commerce",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; //Bu liste, Spring Security'nin kullanıcının yetkilerini anlaması için gereklidir.
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
