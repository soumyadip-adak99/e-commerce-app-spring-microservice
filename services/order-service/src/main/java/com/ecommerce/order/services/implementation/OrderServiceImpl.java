package com.ecommerce.order.services.implementation;

import com.ecommerce.order.records.OrderRequest;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Integer createOrder(OrderRequest request) {
        // check the customer


        // purchases the products -> product microservice

        // persist order lines

        // start payment process

        // send the order confirmation -> notification-microservice (kafka)

        return 0;
    }
}
