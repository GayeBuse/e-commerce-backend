package com.workintech.ecommercebackend.dto;

public record RegisterUser(String fullName,String email,String password,Long roleId) {
}
//RegisterUser: Yeni kullanıcı kaydı oluşturulurken kullanılan verileri temsil eder.
// fullName, email, password ve roleId özelliklerini içerir. Genellikle kullanıcı kaydı oluşturmak için kullanılır.
