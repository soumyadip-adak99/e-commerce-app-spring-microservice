package com.ecommerce.order.services;

import com.ecommerce.order.records.OrderRequest;

public interface OrderService {

    Integer createOrder(OrderRequest request);
}
