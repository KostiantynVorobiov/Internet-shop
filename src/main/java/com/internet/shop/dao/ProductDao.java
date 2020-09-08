package com.internet.shop.dao;

import com.internet.shop.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Product create(Product product);

    Optional<Product> getProductById(Long id);

    List<Product> getAll();

    Product update(Product product);

    boolean deleteById(Long id);
}
