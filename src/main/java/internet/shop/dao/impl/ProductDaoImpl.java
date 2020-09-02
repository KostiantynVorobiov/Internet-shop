package internet.shop.dao.impl;

import internet.shop.dao.ProductDao;
import internet.shop.lib.Dao;
import internet.shop.models.Product;

import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
