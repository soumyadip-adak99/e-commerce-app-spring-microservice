package com.ecommerce.order.services;

import com.ecommerce.order.records.OrderLineRequest;
import com.ecommerce.order.records.OrderLineResponse;

import java.util.List;

public interface OrderLineService {

    void saveOrderLine(OrderLineRequest request);

    List<OrderLineResponse> getAllOrdersById(Integer orderId);
}
