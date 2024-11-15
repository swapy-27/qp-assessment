package com.quetionpro.grocerystore.controller;

import com.quetionpro.grocerystore.entitites.Product;
import com.quetionpro.grocerystore.interfaces.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grocery-store")
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> getProducts() {
     return productService.getAllProducts();
    }

    @GetMapping("/available-products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Product> availableProducts() {
        return productService.getAvailableProducts();
    }
    @PostMapping("/add-products")
    @PreAuthorize("hasRole('ADMIN')")
    public void addProducts(@RequestBody List<Product> products) {
         productService.addProducts(products);
    }
    @PostMapping("/update-products")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProducts(@RequestBody List<Product> products) {
        productService.updateProducts(products);
    }

    @PostMapping("/remove-products")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProducts(@RequestBody List<Integer> productIds) {
        productService.removeProducts(productIds);
    }
}
