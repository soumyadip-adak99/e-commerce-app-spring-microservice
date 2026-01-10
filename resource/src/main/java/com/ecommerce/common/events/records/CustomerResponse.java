package com.ecommerce.common.events.records;

public record CustomerResponse(
        String id,

        String firstname,

        String lastname,

        String email
) {
}
