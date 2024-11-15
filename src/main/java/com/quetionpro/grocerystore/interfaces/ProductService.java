package com.quetionpro.grocerystore.interfaces;

import com.quetionpro.grocerystore.entitites.Product;

import java.util.List;


public interface ProductService extends Observer{
    List<Product> getAllProducts();
    List<Product> getAvailableProducts();
    void removeProducts(List<Integer> products);
    void updateProducts(List<Product> products);
    void addProducts(List<Product> products);
}
