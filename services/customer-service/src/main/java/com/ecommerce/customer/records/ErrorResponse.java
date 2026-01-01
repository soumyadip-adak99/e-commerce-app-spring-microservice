package com.ecommerce.customer.records;

import java.util.Map;

public record ErrorResponse(Map<String, String> error) {

}
