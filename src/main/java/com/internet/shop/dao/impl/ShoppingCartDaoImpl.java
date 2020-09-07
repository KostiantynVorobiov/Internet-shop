package com.internet.shop.dao.impl;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Order;
import com.internet.shop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> getShoppingCardById(Long id) {
        return Storage.shoppingCarts.stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(index -> Storage.shoppingCarts.get(index).equals(shoppingCart.getId()))
                .forEach(index -> Storage.shoppingCarts.set(index, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.shoppingCarts.removeIf(cart -> cart.getId().equals(id));
    }
}
