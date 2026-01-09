package com.ecommerce.customer.controller;

import com.ecommerce.customer.records.CustomerRequest;
import com.ecommerce.customer.records.CustomerResponse;
import com.ecommerce.customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Validated CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request, @PathVariable String id) {
        customerService.updateCustomer(request, id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/exits/{customer-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String id) {
        return ResponseEntity.ok(customerService.exitsUser(id));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customer-id") String id) {
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @DeleteMapping("/delete/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.accepted().build();
    }

}

