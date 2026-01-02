package com.ecommerce.product.exception;

public class ProductPurchaseException extends RuntimeException {

    // args constructor
    public ProductPurchaseException(String message) {
        super(message);
    }

    // no args constructor
    public ProductPurchaseException() { }
}
