package com.workintech.ecommercebackend.controller;


import com.workintech.ecommercebackend.dto.AddressResponse;
import com.workintech.ecommercebackend.entity.Address;
import com.workintech.ecommercebackend.entity.Order;
import com.workintech.ecommercebackend.entity.User;
import com.workintech.ecommercebackend.exceptions.GlobalExceptions;
import com.workintech.ecommercebackend.service.AddressService;
import com.workintech.ecommercebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Validated //gelen isteklerin veya giriş verilerinin belirli kurallara uygun olup olmadığını kontrol etmek için kullanılır.
@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;
    private UserService userService;

    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }
//Belirtilen URI'deki kaynağı almak için kullanılır.
// Bu metot, kaynağın okunmasını ve alınmasını sağlar,
// ancak sunucu tarafında herhangi bir değişiklik yapmaz.
// Örneğin, bir web tarayıcısı bir web sayfasını GET isteğiyle alır.
    @GetMapping
    public List<Address> findAllAddresses(){

        return addressService.findAllAddresses();
    }
    @GetMapping("/{id}")
    public Address getById(@PathVariable long id) {
        return addressService.findById(id);
    }
// Metot içinde `addressService.findById(id)` çağrısı yapılarak,
// belirtilen ID'ye sahip adres `AddressService` üzerinden alınır.
// Bu, veritabanında bu ID'ye sahip adresin aranması ve döndürülmesi anlamına gelir.

    // Belirtilen URI'de yeni bir
    // kaynak oluşturmak için kullanılır. Genellikle, POST isteği ile sunucuya veri
    // gönderilir ve bu veri sunucu tarafında işlenir. Örneğin, bir kullanıcı bir formu
    // doldurduktan sonra bu bilgileri bir sunucuya POST isteğiyle gönderebilir.
    @PostMapping("/{userId}")
    public AddressResponse save(@Validated @PathVariable long userId, @RequestBody Address address) {
        // Kullanıcı ID'sini ve adres bilgilerini içeren bir POST isteği alır. @PostMapping, bu metodu bir HTTP POST isteğine eşler.
        // {userId} ile belirtilen kullanıcı ID'si, @PathVariable kullanılarak URL'den alınır.
        // Adres bilgileri ise @RequestBody kullanılarak isteğin gövdesinden alınır.
        User user = userService.findById(userId);
        // Belirtilen userId ile bir kullanıcıyı arar.
        if (user != null) {
            address.setUser((List<User>) user);
            // Adresin user alanına, bulunan kullanıcı atanır. Bu, adresin hangi kullanıcıya ait olduğunu belirler.
            addressService.save(address);
            // Adres bilgileri veritabanına kaydedilir.
        } else {
            throw new GlobalExceptions("User is not found with id: " + userId, HttpStatus.NOT_FOUND);

        }
        return new AddressResponse(address.getId(), address.getTitle(), address.getFirstName(), address.getSurname(),
                address.getPhone(), address.getCity(), address.getDistrict(), address.getNeighbourhood(),
                address.getAddress());
    }  // Yeni oluşturulan adres bilgilerini içeren bir AddressResponse nesnesi döndürülür. Bu nesne, istemciye yanıt olarak geri gönderilir.

    @PutMapping("/{userId}")
    public Address update(@RequestBody Address address, @PathVariable long userId) {
        // Kullanıcıyı ID'ye göre bul
        //Bu metod, kullanıcının ID'si (userId) ve güncellenmiş adres bilgilerini (address) alarak mevcut bir adres kaydını günceller.
        //@PathVariable long userId: URL'den gelen userId parametresini metot parametresi olarak alır.
        //@RequestBody Address address: İstek gövdesinden gelen güncellenmiş adres bilgilerini Address nesnesi olarak alır.
        User user = userService.findById(userId);
        if (user == null) {
            return null;//hata
            //Kullanıcıyı userId ile bulur.
            //userService.findById(userId) metodu, veritabanından userId ile eşleşen kullanıcıyı döndürür.
            //Eğer kullanıcı bulunamazsa, bir GlobalExceptions fırlatır.
        }

        // Kullanıcının adres listesinde güncellenecek adresi bul
        Address foundAddress = null;
        for (Address userAddress : user.getAddresses()) {
            if (userAddress.getId().equals(address.getId())) {
                foundAddress = userAddress;
                break;
            }
        }

        // Adres bulunamazsa hata fırlat
        if (foundAddress == null) {
            throw new GlobalExceptions("Address is not found", HttpStatus.BAD_REQUEST);
        }

        // Adresi güncelle
        foundAddress.setTitle(address.getTitle());
        foundAddress.setFirstName(address.getFirstName());
        foundAddress.setSurname(address.getSurname());
        foundAddress.setPhone(address.getPhone());
        foundAddress.setCity(address.getCity());
        foundAddress.setDistrict(address.getDistrict());
        foundAddress.setNeighbourhood(address.getNeighbourhood());
        foundAddress.setAddress(address.getAddress());

        // Adresi kaydet
        Address updatedAddress = addressService.save(foundAddress);
        return updatedAddress;
    }
    @DeleteMapping("/{id}")
    public Address delete( @PathVariable long id) {
        Address address = addressService.findById(id);
        if (address != null) {
            addressService.delete(id);
            return address;
        }
        throw new GlobalExceptions("Address is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
