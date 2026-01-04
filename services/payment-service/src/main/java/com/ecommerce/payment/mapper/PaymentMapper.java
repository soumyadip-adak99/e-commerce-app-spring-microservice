package com.ecommerce.payment.mapper;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.records.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
