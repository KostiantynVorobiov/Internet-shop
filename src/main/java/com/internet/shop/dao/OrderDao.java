package com.internet.shop.dao;

import com.internet.shop.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> getOrdersById(Long orderId);

    List<Order> getAll();

    List<Order> getOrderByUserId(Long userId);

    boolean delete(Long id);
}
