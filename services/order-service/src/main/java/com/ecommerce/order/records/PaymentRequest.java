package com.ecommerce.order.records;

import com.ecommerce.common.events.enums.PaymentMethod;
import com.ecommerce.common.events.records.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
