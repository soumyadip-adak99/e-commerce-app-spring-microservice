package com.ecommerce.order.controller;

import com.ecommerce.order.records.OrderLineResponse;
import com.ecommerce.order.services.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-line")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("order-id") Integer id) {
        return ResponseEntity.ok(orderLineService.getAllOrdersById(id));
    }
}
