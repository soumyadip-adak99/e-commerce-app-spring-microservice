package com.ecommerce.order.records;

import java.util.Map;

public record ErrorResponse(Map<String, String> error) {

}
