package com.ecommerce.customer.services;

import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request, String id);

    List<CustomerResponse> getAllCustomers();
}
