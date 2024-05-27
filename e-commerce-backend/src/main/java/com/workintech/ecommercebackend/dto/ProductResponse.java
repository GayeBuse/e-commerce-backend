package com.workintech.ecommercebackend.dto;

public record ProductResponse(Long id,String description, String name, Double price,Double rating,Integer sellCount, Integer stock, String image ) {

}
