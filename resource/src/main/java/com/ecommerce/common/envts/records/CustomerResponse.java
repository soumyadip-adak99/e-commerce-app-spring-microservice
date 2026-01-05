package com.ecommerce.common.envts.records;

public record CustomerResponse(
        String id,

        String firstName,

        String lastName,

        String email
) {
}
