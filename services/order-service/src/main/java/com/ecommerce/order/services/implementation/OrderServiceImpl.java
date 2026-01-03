package com.ecommerce.order.services.implementation;

import com.ecommerce.order.client.CustomerClient;
import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.exception.BusinessException;
import com.ecommerce.order.mapper.OrderMapper;
import com.ecommerce.order.producer.OrderProducer;
import com.ecommerce.order.records.*;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineServiceImpl orderLineServiceImpl;
    private final OrderProducer orderProducer;

    @Override
    public Integer createOrder(OrderRequest request) {
        // check the customer
        var customer = this.customerClient.findCustomerById(String.valueOf(request.id()))
                .orElseThrow(() -> new BusinessException(String.format("Can't create order:: No customer exits with the provided id:: %s", request.id())));

        // purchases the products -> product microservice (RestTemplate)
        var purchaseProducts = this.productClient.purchaseProduct(request.products());

        // persist order lines
        var order = this.orderRepository.save(orderMapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineServiceImpl.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //TODO: start payment process

        //send the order confirmation -> notification-microservice (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );


        return order.getId();
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Integer id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order is found with this provided id:: %s", id)));
    }
}
