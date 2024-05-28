package com.workintech.ecommercebackend.exceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
//. Bu, Java'da yaygın olarak kullanılan bir istisna türüdür ve uygulama çalışma zamanında fırlatıldığında işlenebilir.
//httpStatus özelliği: Bu özellik, fırlatılan istisnanın HTTP yanıt durum kodunu temsil eder. Örneğin, 404 NOT FOUND, 500 INTERNAL SERVER ERROR gibi HTTP durum kodları bu özellik aracılığıyla belirtilebilir.
public class GlobalExceptions extends RuntimeException {
    private HttpStatus httpStatus;

    public GlobalExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}