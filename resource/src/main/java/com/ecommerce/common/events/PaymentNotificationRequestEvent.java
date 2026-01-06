package com.ecommerce.common.events;

import com.ecommerce.common.events.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequestEvent(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
