package com.workintech.ecommercebackend.controller;

import com.workintech.ecommercebackend.dto.ProductResponse;
import com.workintech.ecommercebackend.entity.Category;
import com.workintech.ecommercebackend.entity.Product;
import com.workintech.ecommercebackend.exceptions.GlobalExceptions;
import com.workintech.ecommercebackend.service.CategoryService;
import com.workintech.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
@Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<ProductResponse> findAll(){
        List<Product> productList=productService.findAll();
        List<ProductResponse> productResponseList=new ArrayList<>();
        productList.forEach(product -> {
            productResponseList.add(new ProductResponse(product.getId(), product.getDescription(),product.getName(), product.getPrice(),product.getRating()
                    ,product.getSellCount() ,product.getStock(),product.getImage()));
        });
        return  productResponseList;
    }

    @GetMapping("/{id}")
    public ProductResponse finByID(@PathVariable Long id){
        Product product=productService.findById(id);
        if (product!=null){
            return new ProductResponse(product.getId(), product.getDescription(),product.getName(), product.getPrice(),product.getRating()
                    ,product.getSellCount() ,product.getStock(),product.getImage());
        }
        throw  new GlobalExceptions("Product is not found with this id: " + id, HttpStatus.NOT_FOUND);
    }
    @PostMapping("/{categoryId}")
    public ProductResponse save(@PathVariable Long categoryId,@RequestBody Product product){
        Category category=categoryService.findById(categoryId);
        if (categoryId!=null){
            category.getProductList().add(product);
            product.setCategory(category);
            productService.save(product);
        }else {
            throw new GlobalExceptions("Category is not found",HttpStatus.NOT_FOUND);
        }
        return new ProductResponse(product.getId(), product.getDescription(),product.getName(), product.getPrice(),product.getRating()
                ,product.getSellCount() ,product.getStock(),product.getImage());
    }
// Bu metod, aldığı categoryId parametresine göre kategoriyi bulur ve bu kategoriye yeni bir ürün ekler.
// Sonra bu eklenen ürünün detaylarını içeren bir ProductResponse nesnesini oluşturur ve yanıt olarak döndürür.

    @PutMapping("/{categoryId}")
    public ProductResponse update(@PathVariable Long categoryId,@RequestBody Product product){
        Category category=categoryService.findById(categoryId);
        Product newProduct=null;
        for (Product product1:category.getProductList()){
            if (product1.getId()==product.getId()){
                newProduct=product1;
            }

        }
        if (newProduct==null){
            throw new GlobalExceptions("Product is not found",HttpStatus.NOT_FOUND);
        }
        int foundIndexOf= category.getProductList().indexOf(newProduct);
        category.getProductList().set(foundIndexOf,product);
        product.setCategory(category);
        productService.save(product);
        return new ProductResponse(product.getId(), product.getDescription(),product.getName(), product.getPrice(),product.getRating()
                ,product.getSellCount() ,product.getStock(),product.getImage());

    }
//güncellenmek istenen ürünün bulunması için kullanılan indexOf işlemi,
// listedeki bir öğenin konumunu belirleyerek güncelleme işlemini hızlandırmaya yardımcı olur.

//Özellikle, listede bulunan bir öğenin endeksini bilerek, bu öğeyi güncellemenin ve yerine yeni bir öğe
// yerleştirmenin daha verimli olduğu durumlar için indexOf kullanılır. Bu, özellikle listenin büyüklüğü arttıkça
// performans avantajı sağlar
    }

