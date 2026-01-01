package com.ecommerce.product.services.implementation;

import com.ecommerce.product.records.ProductPurchaseRequest;
import com.ecommerce.product.records.ProductPurchaseResponse;
import com.ecommerce.product.records.ProductRequest;
import com.ecommerce.product.records.ProductResponse;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Integer createProduct(ProductRequest request) {
        return 0;
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests) {
        return List.of();
    }

    @Override
    public ProductResponse getProductById(String id) {
        return null;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return List.of();
    }
}
