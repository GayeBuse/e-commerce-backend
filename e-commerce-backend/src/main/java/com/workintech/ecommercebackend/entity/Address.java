package com.workintech.ecommercebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //Java sınıflarını veritabanı tablolarına eşlemek için kullanılan bir anotasyondur.
@Table(name="address",schema="e-commerce")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "neighbourhood")
    private String neighbourhood;
    @Column(name = "phone")
    private  String phone;
    @Column(name = "title")
    private String title;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "address_user",schema = "e-commerce",joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "address")
    private List<Order> orderList;
}
