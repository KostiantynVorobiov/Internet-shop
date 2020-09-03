package com.internet.shop.dao.impl;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.Product;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> getById(Long id) {
        for (int i = 0; i < Storage.products.size(); i++) {
            if (Storage.products.get(i).getId() == id) {
                return Optional.ofNullable(Storage.products.get(i));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        for (int i = 0; i < Storage.products.size(); i++) {
            if (Storage.products.get(i).getId().equals(product.getId())) {
                return Storage.products.set(i, product);
            }
        }
        return product;
    }

    @Override
    public boolean deleteById(Long id) {
        for (int i = 0; i < Storage.products.size(); i++) {
            if (Storage.products.get(i).getId() == id) {
                Storage.products.remove(i);
            }
        }
        return true;
    }
}
