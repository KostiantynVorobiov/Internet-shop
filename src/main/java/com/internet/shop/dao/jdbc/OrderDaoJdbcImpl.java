package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.exeption.DataOperationException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Order;
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
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public List<Order> getOrderByUserId(Long userId) {
        String query = "SELECT * FROM orders WHERE deleted = false AND user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                orderList.add(getOrderFromResultSet(resultSet, connection));
            }
            return orderList;
        } catch (SQLException e) {
            throw new DataOperationException("Can't get orders by user id: " + userId, e);
        }
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            addProductToOrder(order, connection);
            return order;
        } catch (SQLException e) {
            throw new DataOperationException("Can't create order", e);
        }
    }

    @Override
    public Optional<Order> getById(Long orderId) {
        String query = "SELECT * FROM orders WHERE deleted = false AND order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet, connection);
                return Optional.of(order);
            }
        } catch (SQLException e) {
            throw new DataOperationException("Can't get order by id " + orderId, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                orderList.add(getOrderFromResultSet(resultSet, connection));
            }
            return orderList;
        } catch (SQLException e) {
            throw new DataOperationException("Can't get all orders", e);
        }
    }

    @Override
    public Order update(Order order) {
        String query = "DELETE FROM orders_products WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, order.getId());
            preparedStatement.executeUpdate();
            addProductToOrder(order, connection);
            return order;
        } catch (SQLException e) {
            throw new DataOperationException("Can't update order with id: " + order.getId(), e);
        }
    }

    @Override
    public boolean deleteById(Long orderId) {
        String query = "UPDATE orders SET deleted = TRUE WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, orderId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataOperationException("Can't delete order by id: " + orderId, e);
        }
    }

    private void addProductToOrder(Order order, Connection connection) throws SQLException {
        String query = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (Product product : order.getProducts()) {
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setLong(2, product.getId());
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
    }

    private Order getOrderFromResultSet(ResultSet resultSet, Connection connection)
            throws SQLException {
        long orderId = resultSet.getLong("order_id");
        long userId = resultSet.getLong("user_id");
        Order order = new Order(userId);
        order.setId(orderId);
        order.setProducts(getProductsFromOrder(orderId, connection));
        return order;
    }

    private List<Product> getProductsFromOrder(long orderId, Connection connection)
            throws SQLException {
        String query = "SELECT products.product_id, name, price FROM products \n"
                + "JOIN orders_products ON products.product_id = orders_products.product_id \n"
                + "WHERE order_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, orderId);
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
