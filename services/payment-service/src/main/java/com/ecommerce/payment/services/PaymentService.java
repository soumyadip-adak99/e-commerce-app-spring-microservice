package com.ecommerce.payment.services;

import com.ecommerce.payment.records.PaymentRequest;

public interface PaymentService {

    Integer createPayment(PaymentRequest paymentRequest);
}
