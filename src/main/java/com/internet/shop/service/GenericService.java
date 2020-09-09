package com.internet.shop.service;

import java.util.List;

public interface GenericService<T, K> {

    T getById(K id);

    List<T> getAll();

    boolean deleteById(K id);
}
