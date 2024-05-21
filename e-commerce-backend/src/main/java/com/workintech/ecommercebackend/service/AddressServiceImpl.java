package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Address;
import com.workintech.ecommercebackend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {


    private AddressRepository addressRepository;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(long id) {//Bu metod, belirtilen id'ye sahip Address kaydını bulmaya çalışır ve sonucu bir Optional<Address> içinde döner.
       // Optional sınıfı, null değeri ile uğraşmak yerine boş veya dolu bir kapsayıcı kullanarak daha güvenli bir şekilde nesne işlemleri yapmamıza olanak tanır.
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            return optionalAddress.get();
        }

        return null; //hata fırlatılacak
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
       return null;//hata fırlatılacak
    }
}
