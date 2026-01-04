package com.ecommerce.notification.records;

import com.ecommerce.notification.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        Customer customer,

        List<Product> products
) {
}
