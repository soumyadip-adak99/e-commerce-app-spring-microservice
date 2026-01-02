package com.ecommerce.product.records;

import java.util.Map;

public record ErrorResponse(Map<String, String> error) {

}
