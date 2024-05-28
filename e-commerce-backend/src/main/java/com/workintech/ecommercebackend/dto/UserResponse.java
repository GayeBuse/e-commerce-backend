package com.workintech.ecommercebackend.dto;

public record UserResponse(Long id, String name,String email) {
}
//Bu kayıt tipleri arasındaki farklar, taşıdıkları verilerin türü ve kullanım senaryolarıyla ilgilidir.
// Örneğin, UserResponse sadece kullanıcı bilgilerini döndürürken,
// UserAndAddressResponse aynı zamanda kullanıcının adreslerini de içerir.
// RegisterUser ise yeni bir kullanıcı kaydı oluşturulurken kullanılan verileri temsil eder.