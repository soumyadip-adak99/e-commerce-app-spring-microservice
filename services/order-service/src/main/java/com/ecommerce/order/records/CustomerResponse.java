package com.ecommerce.order.records;

public record CustomerResponse(
        String id,

        String firstName,

        String lastName,

        String email
) {
}
