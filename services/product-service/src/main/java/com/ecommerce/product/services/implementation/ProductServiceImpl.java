package com.ecommerce.product.services.implementation;

import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.ProductPurchaseException;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.records.ProductPurchaseRequest;
import com.ecommerce.product.records.ProductPurchaseResponse;
import com.ecommerce.product.records.ProductRequest;
import com.ecommerce.product.records.ProductResponse;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Integer createProduct(ProductRequest request) {
        var product = productMapper.toProduct(request);
        var saveProduct = productRepository.save(product);
        return saveProduct.getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests) {
        // extract list of product ids
        List<Integer> productIds = requests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();


        // check all the product present in the DB
        // and create a method @findAllByIdInOrderById(@param productIds)
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        // here we check if the products quantity are matches
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exits.");
        }

        // extract ids from stored request
        List<ProductPurchaseRequest> storedRequest = requests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        List<ProductPurchaseResponse> productPurchaseList = new ArrayList<>();

        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedRequest.get(i);

            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException(String.format("Insufficient stock quantity for with product id: %s", productRequest.productId()));
            }

            double newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            productPurchaseList.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

        return productPurchaseList;
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Product not found with id:: %s", id))
                );
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
