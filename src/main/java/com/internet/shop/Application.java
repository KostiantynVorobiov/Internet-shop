package com.internet.shop;

import com.internet.shop.dao.jdbc.ProductDaoJdbcImpl;
import com.internet.shop.model.Product;

public class Application {
    public static void main(String[] args) {

        System.out.println("Test product");
        ProductDaoJdbcImpl productDaoJdbc = new ProductDaoJdbcImpl();
        Product motorola = new Product("Motorola", 50.50);
        Product samsung = new Product("Samsung", 7000.55);
        Product honor = new Product("Honor", 5256.85);
        Product huawei = new Product("Huawei", 4569.25);
        Product xiaomi = new Product("Xiaomi", 3245.10);
        Product nokia = new Product("Nokia", 6215.99);
        System.out.println(samsung.getId());

        productDaoJdbc.create(samsung);
        System.out.println(samsung.getId());
        productDaoJdbc.create(motorola);
        System.out.println(samsung.getId());
//        productDaoJdbc.getById(samsung.getId());
//        System.out.println(productDaoJdbc.getById(samsung.getId()));
//        System.out.println(productDaoJdbc.create(samsung));
//        System.out.println();

//        System.out.println("Test users");
//        UserService userService = (UserService) injector.getInstance(UserService.class);
//        User alkapone = new User("Alkapone", "Alkaponchil_2004", "12346789");
//        User mcClane = new User("McClane", "oreshek.2008", "911911");
//        User afonya = new User("Afonya", "golubi.1978", "1111");
//        User afanasii = new User("Afanasii", "Ivanov.2020", "13579");
//        userService.create(alkapone);
//        userService.create(mcClane);
//        userService.create(afonya);
//        userService.create(afanasii);
//        System.out.println(userService.getAll());
//        for (User user : userService.getAll()) {
//            System.out.println(user);
//        }
//        System.out.println("Get user by Id " + userService.getById(mcClane.getId()));
//        afonya.setPassword("1978.lova.lova");
//        System.out.println("After update " + userService.update(afonya));
//        userService.deleteById(afanasii.getId());
//        for (User user : userService.getAll()) {
//            System.out.println(user);
//        }
//        System.out.println();
//
//        System.out.println("Test shoppingCart");
//        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
//                .getInstance(ShoppingCartService.class);
//        ShoppingCart shoppingCartAlkapone = new ShoppingCart(alkapone.getId());
//        ShoppingCart shoppingCartMcClane = new ShoppingCart(mcClane.getId());
//        ShoppingCart shoppingCartAfonya = new ShoppingCart(afonya.getId());
//        shoppingCartService.create(shoppingCartAlkapone);
//        shoppingCartService.create(shoppingCartMcClane);
//        shoppingCartService.create(shoppingCartAfonya);
//        shoppingCartService.addProduct(shoppingCartAfonya, samsung);
//        shoppingCartService.addProduct(shoppingCartAlkapone, honor);
//        shoppingCartService.addProduct(shoppingCartAlkapone, samsung);
//        shoppingCartService.addProduct(shoppingCartAlkapone, xiaomi);
//        shoppingCartService.addProduct(shoppingCartMcClane, nokia);
//        shoppingCartService.addProduct(shoppingCartMcClane, motorola);
//        shoppingCartService.addProduct(shoppingCartMcClane, huawei);
//        System.out.println("Alkapone's cart: " + shoppingCartService.getByUserId(alkapone.getId()));
//        System.out.println("McClane's cart: " + shoppingCartService.getByUserId(mcClane.getId()));
//        shoppingCartService.deleteProduct(shoppingCartMcClane, motorola);
//        System.out.println("McClane's cart after delete product: "
//                + shoppingCartService.getByUserId(mcClane.getId()));
//        shoppingCartService.clear(shoppingCartMcClane);
//        System.out.println("McClane's cart after clear: "
//                + shoppingCartService.getByUserId(mcClane.getId()));
//        shoppingCartService.deleteById(shoppingCartMcClane.getId());
//        System.out.println();
//
//        System.out.println("Test order");
//        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
//        orderService.completeOrder(shoppingCartAlkapone);
//        orderService.completeOrder(shoppingCartAfonya);
//        orderService.completeOrder(shoppingCartService.getByUserId(afonya.getId()));
//        System.out.println("Alkapone order: " + orderService.getUserOrders(alkapone.getId()));
//        orderService.deleteById(afonya.getId());
//        System.out.println(orderService.getAll());
//        for (Order order : orderService.getAll()) {
//            System.out.println(order);
//        }
    }
}
