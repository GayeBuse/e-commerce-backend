package com.workintech.ecommercebackend.dto;

import com.workintech.ecommercebackend.entity.Address;

import java.util.List;

public record UserAndAddressResponse(Long id, String name, String email, List<Address>addressList) {
}
