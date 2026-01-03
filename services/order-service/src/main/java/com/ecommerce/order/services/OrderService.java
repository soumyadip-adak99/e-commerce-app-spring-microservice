package com.ecommerce.order.services;

import com.ecommerce.order.records.OrderRequest;
import com.ecommerce.order.records.OrderResponse;

import java.util.List;

public interface OrderService {

    Integer createOrder(OrderRequest request);

    List<OrderResponse> getAll();

    OrderResponse getOrderById(Integer id);
}
