package com.workintech.ecommercebackend.dto;
// DTO'lar, genellikle veritabanından alınan veya istemcilere
// gönderilen verilerin taşınmasında kullanılır. Controller katmanı
// genellikle istemciden gelen istekleri alır, işler, gerekli iş mantığını
// yürütür ve sonuçları kullanıcıya veya istemciye gönderir.
//Bu bağlamda, AddressResponse adlı DTO sınıfı, controller
// katmanında HTTP isteklerine yanıt olarak döndürülecek adres
// bilgilerini temsil eder. Controller, isteğe bağlı olarak bu
// sınıfın nesnelerini oluşturur ve döndürür. Örneğin, bir adres
// oluşturulduğunda veya güncellendiğinde, controller bu AddressResponse
// sınıfından bir nesne oluşturur ve istemciye geri gönderir.
// Bu şekilde, DTO sınıfları controller katmanının, özellikle
// istemciye veri gönderme veya istemciden veri alma süreçlerinde,
// verileri temsil etmek için kullandığı bir araç olabilir.
public record AddressResponse(Long id, String firstName,String surname,String address,String city,String district,String neighbourhood, String phone, String title) {
}
