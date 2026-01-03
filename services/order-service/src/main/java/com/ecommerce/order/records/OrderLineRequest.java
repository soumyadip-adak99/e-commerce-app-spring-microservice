package com.ecommerce.order.records;

public record OrderLineRequest(
        Integer id,

        Integer orderId,

        Integer productId,

        double quantity
) {
}
