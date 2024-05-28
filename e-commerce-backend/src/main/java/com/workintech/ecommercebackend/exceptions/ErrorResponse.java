package com.workintech.ecommercebackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Error response oluşturmak, kullanıcıya daha anlamlı ve
// yapılandırılmış hata mesajları göndermek için önemlidir.
// Bu mesajlar, hata türünü, açıklamasını ve gerektiğinde hatanın
// nerede olduğunu belirtebilir.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}