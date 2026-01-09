package com.ecommerce.payment.controller;

import com.ecommerce.payment.records.PaymentRequest;
import com.ecommerce.payment.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }
}
