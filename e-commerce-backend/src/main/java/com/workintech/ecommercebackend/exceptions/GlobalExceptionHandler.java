package com.workintech.ecommercebackend.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j //Günlükleme (logging), bir yazılımın çalışması sırasında
// oluşan olayları kaydetme işlemidir. Bu olaylar, uygulamanın
// durumu, hata ve hata ayıklama bilgileri gibi çeşitli bilgileri
// içerebilir. Günlükleme, bir uygulamanın performansını izleme,
// hata ayıklama, hata tespiti, izleme ve raporlama gibi birçok
// farklı amaç için kullanılır.
// bilgi toplamak ve hata ayıklama sürecini yönetmek daha zor olabilir.
@ControllerAdvice
//Bu anotasyon, global hata yakalama için kullanılır ve
// bu sınıfın bir global exception handler olduğunu belirtir.
// Uygulamadaki tüm controller'lar için geçerlidir.
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(GlobalExceptions globalExceptions) {
        log.error("Global error occurred. Error message: " + globalExceptions.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(globalExceptions.getHttpStatus().value(), globalExceptions.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, globalExceptions.getHttpStatus());
    }
//: Bu metot, özel bir tür olan GlobalExceptions istisnasını
// yakalar. Bu istisna, özel durumlar için oluşturulmuş hataları
// temsil eder. Örneğin, belirli bir durumda istemciye belirli bir
// HTTP durum koduyla yanıt vermek istediğinizde kullanılabilir.
// Metot, istisna durumunda bir log kaydı oluşturur, ardından
// ErrorResponse nesnesi ile istemciye uygun bir HTTP durumu ve
// hata mesajıyla birlikte yanıt döner.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception) {
        log.error("Error occurred. Error message: " + exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
//: Bu metot, genel olarak herhangi bir istisnayı yakalar.
// Özel bir tür belirtilmediğinde, bu metot çalışır. Herhangi bir
// beklenmeyen istisna durumunda çalışır ve bunu loglar. Ardından,
// yine ErrorResponse nesnesi ile birlikte içsel sunucu hatası
// durumu (500) ve hata mesajıyla birlikte istemciye yanıt döner.