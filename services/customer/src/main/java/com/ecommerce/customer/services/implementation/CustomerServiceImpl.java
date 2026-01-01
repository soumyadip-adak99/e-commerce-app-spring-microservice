package com.ecommerce.customer.services.implementation;

import com.ecommerce.customer.records.CustomerRequest;
import com.ecommerce.customer.records.CustomerResponse;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.mapper.CustomerMapper;
import com.ecommerce.customer.repository.CustomerRepository;
import com.ecommerce.customer.services.CustomerService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request, String id) {
        var customer = repository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException(String.format("Cannot update Customer:: No Customer found with provided ID:: %s", id))
                );

        mergeCustomer(customer, request);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exitsUser(String id) {
        return repository.findById(id).isPresent();
    }

    @Override
    public CustomerResponse findCustomerById(String id) {
        return repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with this provided id %s", id)));
    }

    @Override
    public void deleteCustomer(String id) {
        if(!exitsUser(id)) {
            throw new RuntimeException("User not found");
        }
        repository.deleteById(id);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }

        if (StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());
        }

        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }

        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }
}
