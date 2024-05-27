package com.workintech.ecommercebackend.controller;

import com.workintech.ecommercebackend.dto.OrderResponse;
import com.workintech.ecommercebackend.entity.Address;
import com.workintech.ecommercebackend.entity.Order;
import com.workintech.ecommercebackend.entity.Product;
import com.workintech.ecommercebackend.exceptions.GlobalExceptions;
import com.workintech.ecommercebackend.service.AddressService;
import com.workintech.ecommercebackend.service.OrderService;
import com.workintech.ecommercebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private AddressService addressService;
@Autowired
    public OrderController(OrderService orderService, UserService userService, AddressService addressService) {
        this.orderService = orderService;
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping
    public List<Order> findAll(){
        return orderService.findAll();
    }
    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id){
        Order order = orderService.findById(id);
        if (order != null) {
            return new OrderResponse(order.getId(), order.getProductList(), order.getUser(), order.getAddress());
        } else {
            return null;
        }
    }
    @PostMapping("/{addressId}")
    public OrderResponse save(@PathVariable Long addressId, @RequestBody Order order) {
        Address address = addressService.findById(addressId);
        if (address != null) {
            address.getOrderList().add(order);// Adresin sipariş listesine yeni siparişi ekleriz.
            order.setAddress(address);
            List<Product> productList = order.getProductList();
            for (Product product : productList) {
                order.addProduct(product);
            }
            orderService.save(order);
        } else {
            throw new GlobalExceptions("Order is not found with this id: " + addressId, HttpStatus.NOT_FOUND);
        }
        return new OrderResponse(order.getId(), order.getProductList(), order.getUser(), order.getAddress());
    }
    @PutMapping("/{addressId}")
    public OrderResponse update(@RequestBody Order order, @PathVariable long addressId){
        Address address = addressService.findById(addressId);
        Order foundOrder = null;
        for(Order order1:address.getOrderList()){
            if(order1.getId() == order.getId()){
                foundOrder = order1;
                break;
            }
        }
        if(foundOrder == null){
            // Hata durumu: Sipariş bulunamadı
            throw new GlobalExceptions ("Order is not found", HttpStatus.BAD_REQUEST);
        }

        // Sipariş bulunduğunda işlemleri gerçekleştir
        foundOrder.setAddress(address);
        foundOrder.setProductList(order.getProductList());

        // Güncellenmiş siparişi kaydet
        orderService.save(foundOrder);

        // Güncellenmiş siparişi dönüştürerek yanıt olarak döndür
        return new OrderResponse(foundOrder.getId(), foundOrder.getProductList(), foundOrder.getUser(), foundOrder.getAddress());
    }



    @DeleteMapping("/{id}")
    public Order delete(@PathVariable Long id){
        Order order=orderService.findById(id);
        if (order!=null){
            orderService.delete(id);
            return order;
        }
        throw new GlobalExceptions("Order is not found with this id: " + id,HttpStatus.NOT_FOUND);

    }

}