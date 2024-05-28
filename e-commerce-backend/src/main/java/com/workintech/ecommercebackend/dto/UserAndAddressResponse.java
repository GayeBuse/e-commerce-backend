package com.workintech.ecommercebackend.dto;

import com.workintech.ecommercebackend.entity.Address;

import java.util.List;

public record UserAndAddressResponse(Long id, String name, String email, List<Address>addressList) {
}
//: Kullanıcı bilgilerini ve kullanıcının birden fazla adresini temsil eder.
// id, name, email ve kullanıcının adreslerini içeren bir List<Address> özelliğini içerir.
// Kullanıcı detaylarına ek olarak, kullanıcının birden fazla adresini de döndürmek için kullanılır.