package com.ecommerce.order.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public class CustomerClient {


}
