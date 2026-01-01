package com.ecommerce.customer.records;

import com.ecommerce.customer.entity.Address;


public record CustomerResponse(

        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {

}
