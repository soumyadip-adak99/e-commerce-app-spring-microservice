package com.ecommerce.common.events.records;

public record CustomerResponse(
        String id,

        String firstName,

        String lastName,

        String email
) {
}
