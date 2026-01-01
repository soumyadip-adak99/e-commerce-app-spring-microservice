package com.ecommerce.customer.records;

import com.ecommerce.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,

        @NotNull(message = "Customer firstname is required")
        String firstname,

        @NotNull(message = "Customer email is required")
        String lastname,

        @NotNull(message = "Customer email is required")
        @Email(message = "Customer email is not valid email address")
        String email,

        Address address
) {
}
