package com.internet.shop;

import com.internet.shop.dao.jdbc.ProductDaoJdbcImpl;
import com.internet.shop.model.Product;

public class Application {
    public static void main(String[] args) {
        ProductDaoJdbcImpl productDaoJdbc = new ProductDaoJdbcImpl();
        Product honor = new Product("Honor", 5362.52);
        Product huawei = new Product("Huawei", 4569.25);
        Product xiaomi = new Product("Xiaomi", 3245.10);
        Product motorola = new Product("Motorola", 50.50);
        productDaoJdbc.create(huawei);
        productDaoJdbc.create(honor);
        productDaoJdbc.create(xiaomi);
        System.out.println("Get product " + honor.getId());
        System.out.println("Get product by id " + productDaoJdbc.getById(honor.getId()).get());
        System.out.println(productDaoJdbc.getAll());
        xiaomi.setId(xiaomi.getId() + 2);
        System.out.println("Update product " + productDaoJdbc.update(xiaomi));
        System.out.println(productDaoJdbc.getAll());
        productDaoJdbc.deleteById(xiaomi.getId());
        System.out.println(productDaoJdbc.getAll());
    }
}
