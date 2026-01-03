package com.ecommerce.payment.services.implementation;

import com.ecommerce.payment.records.PaymentRequest;
import com.ecommerce.payment.repository.PaymentRepository;
import com.ecommerce.payment.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Integer createPayment(PaymentRequest paymentRequest) {
        return 0;
    }
}
