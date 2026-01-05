package com.ecommerce.common.envts;

import com.ecommerce.common.envts.enums.PaymentMethod;
import com.ecommerce.common.envts.records.CustomerResponse;
import com.ecommerce.common.envts.records.PurchaseResponse;

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
