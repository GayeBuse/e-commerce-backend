package com.workintech.ecommercebackend.repository;

import com.workintech.ecommercebackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
// Bu, her bir entity sınıfının veritabanı işlemlerini yönetmek için bir repository sınıfına sahip olması anlamına gelir.
// Bu yaklaşım, veri erişim katmanının modüler ve yönetilebilir olmasını sağlar
//JpaRepository arayüzünü genişlettiği için AddressRepository şu temel CRUD işlemlerini sağlar:
//Create: Yeni bir Address entity'sini veri tabanına eklemek.
//Read: Mevcut Address entity'lerini veri tabanından sorgulamak.
//Update: Mevcut bir Address entity'sini güncellemek.
//Delete: Mevcut bir Address entity'sini veri tabanından silmek.
public interface AddressRepository extends JpaRepository<Address,Long> {
}
