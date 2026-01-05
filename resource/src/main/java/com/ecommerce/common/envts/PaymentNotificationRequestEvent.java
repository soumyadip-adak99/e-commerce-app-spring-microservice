package com.ecommerce.common.envts;

import com.ecommerce.common.envts.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequestEvent(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
