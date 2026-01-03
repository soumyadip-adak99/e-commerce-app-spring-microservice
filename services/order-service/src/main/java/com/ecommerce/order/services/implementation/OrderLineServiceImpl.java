package com.ecommerce.order.services.implementation;

import com.ecommerce.order.mapper.OrderLineMapper;
import com.ecommerce.order.records.OrderLineRequest;
import com.ecommerce.order.records.OrderLineResponse;
import com.ecommerce.order.repository.OrderLineRepository;
import com.ecommerce.order.services.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

//    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
//        var order = orderLineMapper.toOrderLine(orderLineRequest);
//        return  orderLineRepository.save(order).getId();
//    }

    @Override
    public void saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        orderLineRepository.save(order);
    }

    @Override
    public List<OrderLineResponse> getAllOrdersById(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
