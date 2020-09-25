package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.exeption.DataOperationException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
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
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public Optional<ShoppingCart> getShoppingCartByUserId(Long userId) {
        String query = "SELECT * FROM shopping_carts WHERE deleted = false AND user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet, connection);
                return Optional.of(shoppingCart);
            }
        } catch (SQLException e) {
            throw new DataOperationException("Can't get shopping cart by user id " + userId, e);
        }
        return Optional.empty();
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_carts (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingCart.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
            addProductsToShoppingCart(shoppingCart, connection);
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataOperationException("Can't create shopping cart", e);
        }
    }

    @Override
    public Optional<ShoppingCart> getById(Long id) {
        String query = "SELECT * FROM shopping_carts WHERE deleted = false AND cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShoppingCart shoppingCart = getShoppingCartFromResultSet(resultSet, connection);
                return Optional.of(shoppingCart);
            }
        } catch (SQLException e) {
            throw new DataOperationException("Can't get shopping cart by id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<ShoppingCart> getAll() {
        String query = "SELECT * FROM shopping_carts WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ShoppingCart> shoppingCartList = new ArrayList<>();
            while (resultSet.next()) {
                shoppingCartList.add(getShoppingCartFromResultSet(resultSet, connection));
            }
            return shoppingCartList;
        } catch (SQLException e) {
            throw new DataOperationException("Can't get all carts", e);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        String query = "DELETE FROM shopping_carts_products WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, shoppingCart.getId());
            preparedStatement.executeUpdate();
            addProductsToShoppingCart(shoppingCart, connection);
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataOperationException("Can't update shopping cart", e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "UPDATE shopping_carts SET deleted = TRUE WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataOperationException("Can't delete shopping cart with id " + id, e);
        }
    }

    private void addProductsToShoppingCart(ShoppingCart shoppingCart, Connection connection)
            throws SQLException {
        String query = "INSERT INTO shopping_carts_products (cart_id, product_id) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (Product product : shoppingCart.getProducts()) {
            preparedStatement.setLong(1, shoppingCart.getId());
            preparedStatement.setLong(2, product.getId());
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
    }

    private ShoppingCart getShoppingCartFromResultSet(ResultSet resultSet, Connection connection)
            throws SQLException {
        Long cartId = resultSet.getLong("cart_id");
        Long userId = resultSet.getLong("user_id");
        ShoppingCart shoppingCart = new ShoppingCart(userId);
        shoppingCart.setId(cartId);
        shoppingCart.setProducts(getProductsFromShoppingCart(cartId, connection));
        return shoppingCart;
    }

    private List<Product> getProductsFromShoppingCart(Long cartId, Connection connection)
            throws SQLException {
        String query = "SELECT products.product_id, name, price FROM products \n"
                + "JOIN shopping_carts_products "
                + "ON products.product_id = shopping_carts_products.product_id \n"
                + "WHERE cart_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, cartId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            Long productId = resultSet.getLong("product_id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            Product product = new Product(name, price);
            product.setId(productId);
            productList.add(product);
        }
        preparedStatement.close();
        return productList;
    }
}
