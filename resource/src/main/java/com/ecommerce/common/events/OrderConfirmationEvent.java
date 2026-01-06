package com.ecommerce.common.events;

import com.ecommerce.common.events.enums.PaymentMethod;
import com.ecommerce.common.events.records.CustomerResponse;
import com.ecommerce.common.events.records.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationEvent(
        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        CustomerResponse customer,

        List<PurchaseResponse> products
) {
}
