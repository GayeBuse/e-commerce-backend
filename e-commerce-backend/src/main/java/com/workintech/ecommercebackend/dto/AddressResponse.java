package com.workintech.ecommercebackend.dto;

public record AddressResponse(Long id, String firstName,String surname,String address,String city,String district,String neighbourhood, String phone, String title) {
}
