package com.workintech.ecommercebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//code
// "k:ayakkabi"
//gender
//        "k"
//id
//        2
//img
//        "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_kadın_ayakkabı.jpg"
//rating
//        4.9
//title
//        "Ayakkabı"
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "category",schema = "e-commerce")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "image")
    private  String image;
    @Column(name = "title")
    private  String title;
    @Column(name = "rating")
    private Double rating;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")//Bu anotasyon, bir Category varlığının birçok Product varlığıyla ilişkili olduğunu belirtir.
    private List<Product> productList;

}
