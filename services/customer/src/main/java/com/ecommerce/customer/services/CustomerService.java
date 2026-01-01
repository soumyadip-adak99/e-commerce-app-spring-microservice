package com.ecommerce.customer.services;

import com.ecommerce.customer.records.CustomerRequest;
import com.ecommerce.customer.records.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request, String id);

    List<CustomerResponse> getAllCustomers();

    boolean exitsUser(String id);

    CustomerResponse findCustomerById(String id);

    void deleteCustomer(String id);
}
