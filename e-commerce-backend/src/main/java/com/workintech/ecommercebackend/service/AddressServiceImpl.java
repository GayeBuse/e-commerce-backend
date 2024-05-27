package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Address;
import com.workintech.ecommercebackend.exceptions.GlobalExceptions;
import com.workintech.ecommercebackend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Bu kod parçacığı, bir Spring Boot
// uygulamasında adres işlemlerini
// gerçekleştirmek için kullanılan bir
// hizmet katmanını (service layer) temsil
// ediyor. İki ayrı sınıf var çünkü tipik bir tasarım
// deseni olan "Dependency Injection" (Bağımlılık Enjeksiyonu) kullanıldı.
//Bu yapı, kodun modülerliğini ve bakımını artırır. Bağımlılık Enjeksiyonu
// sayesinde, AddressServiceImpl sınıfı AddressRepository'ye ihtiyaç duyar ve
// bu bağımlılık Spring tarafından otomatik olarak enjekte edilir. Bu, sınıflar
// arasındaki sıkı bağımlılığı azaltır ve değişikliklerin kolayca yapılmasını sağlar.
// Ayrıca, bu yapı test edilebilirlik açısından da avantaj sağlar.
@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }


    @Override
    public Address findById(long id) {//Bu metod, belirtilen id'ye sahip Address kaydını bulmaya çalışır ve sonucu bir Optional<Address> içinde döner.
       // Optional sınıfı, null değeri ile uğraşmak yerine boş veya dolu bir kapsayıcı kullanarak daha güvenli bir şekilde nesne işlemleri yapmamıza olanak tanır.
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            return optionalAddress.get();
        }

        throw new GlobalExceptions("Address is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address delete(long id) {
        Address address = findById(id);
        if(address != null){
            addressRepository.delete(address);
            return address;
        }
        throw new GlobalExceptions("Address is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
