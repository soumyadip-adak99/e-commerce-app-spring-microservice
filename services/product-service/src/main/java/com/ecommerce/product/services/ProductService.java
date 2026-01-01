package com.ecommerce.product.services;

import com.ecommerce.product.records.ProductPurchaseRequest;
import com.ecommerce.product.records.ProductPurchaseResponse;
import com.ecommerce.product.records.ProductRequest;
import com.ecommerce.product.records.ProductResponse;

import java.util.List;

public interface ProductService {

    Integer createProduct(ProductRequest request);

    List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests);

    ProductResponse getProductById(String id);

    List<ProductResponse> getAllProducts();
}
