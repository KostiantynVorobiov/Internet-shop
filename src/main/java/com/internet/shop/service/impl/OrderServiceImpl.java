package com.internet.shop.service.impl;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.Order;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order(shoppingCart.getUserId());
        order.getProducts().addAll(List.copyOf(shoppingCart.getProducts()));
        shoppingCart.getProducts().clear();
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getOrderByUserId(userId);
    }

    @Override
    public Order get(Long id) {
        return orderDao.getOrderById(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
