package com.quetionpro.grocerystore.service;

import com.quetionpro.grocerystore.dtos.OrderEvent;
import com.quetionpro.grocerystore.entitites.OrderItem;
import com.quetionpro.grocerystore.entitites.Product;
import com.quetionpro.grocerystore.interfaces.Observer;
import com.quetionpro.grocerystore.interfaces.ProductService;
import com.quetionpro.grocerystore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAvailableProducts() {
        return productRepository.findByInventoryLevelGreaterThan(0);
    }

    @Override
    public void removeProducts(List<Integer> productsId) {
        productsId.forEach(id -> productRepository.deleteById(id));
    }

    @Override
    public void updateProducts(List<Product> products) {
        products.forEach(product -> {
            Product existingProduct = productRepository.findById(product.getProductId()).orElse(null);
            existingProduct = updateProduct(existingProduct, product);
            productRepository.save(existingProduct);
        });
    }

    Product updateProduct(Product existingProduct, Product newProduct) {
        existingProduct.setProductName(newProduct.getProductName());
        existingProduct.setProductPrice(newProduct.getProductPrice());
        existingProduct.setInventoryLevel(newProduct.getInventoryLevel());
        existingProduct.setUpdatedAt(LocalDateTime.now().toString());
        return existingProduct;
    }

    @Override
    public void addProducts(List<Product> products) {
        products.forEach(product -> {
            product.setCreatedAt(LocalDateTime.now().toString());
            productRepository.save(product);
        });

    }

    @Override
    public void update(OrderEvent orderEvent) {
        List<OrderItem> orderItems = orderEvent.getOrderItems();
        Map<Integer, Product> orderedProducts = orderEvent.getOrderedProducts().stream()
                .collect(Collectors.toMap(
                        Product::getProductId,
                        product -> product
                ));

        List<Product> updatedProducts = orderItems.stream().map(orderItem -> {

            Product product = orderedProducts.get(orderItem.getProductId());
            Long inventoryLevel = product.getInventoryLevel() - orderItem.getQuantity() > 0 ? product.getInventoryLevel() - orderItem.getQuantity() : 0;
            product.setInventoryLevel(inventoryLevel);
            return product;

        }).toList();

        productRepository.saveAll(updatedProducts);

    }
}
