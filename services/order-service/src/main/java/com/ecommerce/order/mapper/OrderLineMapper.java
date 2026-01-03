package com.ecommerce.order.mapper;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderLine;
import com.ecommerce.order.records.OrderLineRequest;
import com.ecommerce.order.records.OrderLineResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine order) {
        return new OrderLineResponse(
                order.getId(),
                order.getQuantity()
        );
    }
}
