package com.ecommerce.payment.services.implementation;

import com.ecommerce.payment.mapper.PaymentMapper;
import com.ecommerce.payment.producer.NotificationProducer;
import com.ecommerce.payment.records.PaymentNotificationRequest;
import com.ecommerce.payment.records.PaymentRequest;
import com.ecommerce.payment.repository.PaymentRepository;
import com.ecommerce.payment.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(paymentMapper.toPayment(request));

        notificationProducer.sendingNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
