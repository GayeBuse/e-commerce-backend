package com.workintech.ecommercebackend.repository;

import com.workintech.ecommercebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// @Query anotasyonunu kullanarak JPQL (Java Persistence Query Language) veya SQL sorgularını doğrudan repository arayüzüne tanımlayabilirsiniz. Böylece özelleştirilmiş sorguları yazabilir ve istediğiniz şekilde sıralama yapabilirsiniz.
public interface ProductRepository extends JpaRepository<Product,Long> {
// Product entity'sindeki price alanına göre artan sıralama yapar. Yani, fiyatı en düşük üründen en yüksek ürüne doğru sıralar.
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllByOrderByPriceAsc();

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> findAllByOrderByPriceDesc();
// Product entity'sindeki rating alanına göre artan sıralama yapar. Yani, puanı en düşük üründen en yüksek ürüne doğru sıralar.
    @Query("SELECT p FROM Product p ORDER BY p.rating ASC")
    List<Product> findAllByOrderByRatingAsc();

    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    List<Product> findAllByOrderByRatingDesc();

//Bu sorgu, belirli bir kategoriye ait tüm ürünleri getirir.
    @Query("SELECT p FROM Product p WHERE p.category.id=:categoryId")
    List<Product> findAllByCategoryId(long categoryId);
//Bu sorgu, belirli bir kategoriye ait ürünleri fiyatlarına göre artan sıralamada getirir.
    @Query("SELECT p FROM Product p WHERE p.category.id=:categoryId ORDER BY p.price ASC")
    List<Product> findAllByCategoryPriceAsc(long categoryId);
//Bu sorgu, belirli bir kategoriye ait ürünleri fiyatlarına göre azalan sıralamada getirir.
    @Query("SELECT p FROM Product p WHERE p.category.id=:categoryId ORDER BY p.price DESC")
    List<Product> findAllByCategoryPriceDesc(long categoryId);
//Bu sorgu, belirli bir kategoriye ait ürünleri puanlarına göre artan sıralamada getirir.
    @Query("SELECT p FROM Product p WHERE p.category.id=:categoryId ORDER BY p.rating ASC")
    List<Product> findAllByCategoryRatingAsc(long categoryId);
//Bu sorgu, belirli bir kategoriye ait ürünleri puanlarına göre azalan sıralamada getirir.
    @Query("SELECT p FROM Product p WHERE p.category.id=:categoryId ORDER BY p.rating DESC")
    List<Product> findAllByCategoryRatingDesc(long categoryId);

}
