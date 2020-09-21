package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.exeption.DataOperationException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {

    @Override
    public Product create(Product item) {
        String query = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                Long productId = resultSet.getLong(1);
                item.setId(productId);
            }
            return item;
        } catch (SQLException e) {
            throw new DataOperationException("Can't create product: " + item.getName(), e);
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        String query = "SELECT * FROM products WHERE deleted = false AND product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Product product = new Product(name, price);
                product.setId(productId);
                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new DataOperationException("Can't get product by id = " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE deleted = false ";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Product product = new Product(name, price);
                product.setId(productId);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            throw new DataOperationException("Can't get all products", e);
        }
    }

    @Override
    public Product update(Product item) {
        String query = "UPDATE products SET name=?, price=? "
                + "WHERE deleted = false AND product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new DataOperationException("Can't update product: " + item.getName(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "UPDATE products SET deleted = true WHERE product_id=? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataOperationException("Can't deleted product with id: " + id, e);
        }
    }
}
