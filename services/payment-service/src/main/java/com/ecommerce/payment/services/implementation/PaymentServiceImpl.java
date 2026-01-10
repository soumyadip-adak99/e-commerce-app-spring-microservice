package com.ecommerce.payment.services.implementation;

import com.ecommerce.common.events.PaymentNotificationRequestEvent;
import com.ecommerce.payment.mapper.PaymentMapper;
import com.ecommerce.payment.producer.NotificationProducer;
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
                new PaymentNotificationRequestEvent(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
