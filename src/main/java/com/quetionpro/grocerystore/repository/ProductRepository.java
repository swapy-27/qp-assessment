package com.quetionpro.grocerystore.repository;

import com.quetionpro.grocerystore.entitites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByInventoryLevelGreaterThan(int inventoryLevel);

}
