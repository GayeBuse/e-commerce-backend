package com.workintech.ecommercebackend.entity;
/*
        {
            "id": 2,
            "name": "Siyah %100 Pamuk",
            "description": "Siyah %100 Pamuk Regular/Normal Kalıp Basic V Yaka Uzun Kollu Örme T-Shirt TWOAW21TS0099",
            "price": 145.99,
            "stock": 84,
            "store_id": 1,
            "category_id": 1,
            "rating": 0.35,
            "sell_count": 923,
            "images": [
                {
                    "url": "https://cdn.dsmcdn.com/ty155/product/media/images/20210806/13/116221695/81629339/1/1_org_zoom.jpg",
                    "index": 0
                }
            ]
        }
 */
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product", schema = "e-commerce")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "sell_count")
    private Integer sellCount;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "image")
    private String image;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    //(Ürün) varlığının birçok örneğinin bir Category (Kategori) varlığına ait olduğunu belirtir
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_order",schema = "e-commerce",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;
}
// her siparişin de birçok ürün içerebileceğini ifade eder.