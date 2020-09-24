package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.Role;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.util.Set;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        System.out.println("Test product");
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product honor = new Product("Honor", 5362.52);
        Product huawei = new Product("Huawei", 4569.25);
        Product xiaomi = new Product("Xiaomi", 3245.10);
        productService.create(huawei);
        productService.create(honor);
        productService.create(xiaomi);
        System.out.println("Get product id " + honor.getId());
        System.out.println("Get product by id " + productService.getById(honor.getId()));
        System.out.println(productService.getAll());
        xiaomi.setPrice(xiaomi.getPrice() + 10000);
        System.out.println("Update product " + productService.update(xiaomi));
        System.out.println(productService.getAll());
        productService.deleteById(xiaomi.getId());
        System.out.println("After delete " + productService.getAll());

        System.out.println("Test users");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        User alkapone = new User("Alkapone", "Alkaponchil_2008", "12346789");
        User mcClane = new User("McClane", "oreshek.2008", "911911");
        User afonya = new User("Afonya", "golubi.1978", "1111");
        User afanasii = new User("Afanasii", "Ivanov.2020", "13579");
        userService.create(alkapone);
        userService.create(mcClane);
        userService.create(afonya);
        userService.create(afanasii);
        alkapone.setRoles(Set.of(Role.of("USER")));
        mcClane.setRoles(Set.of(Role.of("ADMIN")));
        afonya.setRoles(Set.of(Role.of("USER")));
        afanasii.setRoles(Set.of(Role.of("USER")));
        System.out.println("Get user id " + alkapone.getId());
        System.out.println("Get user id " + mcClane.getId());
        System.out.println("Get user id " + afonya.getId());
        System.out.println("Get user id " + afanasii.getId());
        for (User user : userService.getAll()) {
            System.out.println(user);
        }
        System.out.println("Get user by id " + userService.getById(mcClane.getId()));
        mcClane.setName("Pepruchoo");
        System.out.println("Update user " + userService.update(mcClane));
        for (User user : userService.getAll()) {
            System.out.println(user);
        }
        userService.deleteById(afanasii.getId());
        System.out.println("After deleted");
        for (User user : userService.getAll()) {
            System.out.println(user);
        }
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        System.out.println("Test shoppingCart");
        ShoppingCart shoppingCartAlkapone = new ShoppingCart(alkapone.getId());
        ShoppingCart shoppingCartMcClane = new ShoppingCart(mcClane.getId());
        ShoppingCart shoppingCartAfonya = new ShoppingCart(afonya.getId());
        shoppingCartService.create(shoppingCartAlkapone);
        shoppingCartService.create(shoppingCartMcClane);
        shoppingCartService.create(shoppingCartAfonya);
        shoppingCartService.addProduct(shoppingCartAfonya, huawei);
        shoppingCartService.addProduct(shoppingCartAlkapone, honor);
        shoppingCartService.addProduct(shoppingCartAlkapone, xiaomi);

        System.out.println("Test shoppingCart");
        shoppingCartService.create(shoppingCartAlkapone);
        shoppingCartService.create(shoppingCartMcClane);
        shoppingCartService.create(shoppingCartAfonya);

        shoppingCartService.addProduct(shoppingCartAlkapone, honor);
        shoppingCartService.addProduct(shoppingCartAlkapone, xiaomi);
        shoppingCartService.addProduct(shoppingCartMcClane, huawei);

        System.out.println("Alkapone's cart: " + shoppingCartService.getByUserId(alkapone.getId()));
        System.out.println("McClane's cart: " + shoppingCartService.getByUserId(mcClane.getId()));

        shoppingCartService.deleteProduct(shoppingCartMcClane, xiaomi);

        System.out.println("McClane's cart after delete product: "
                + shoppingCartService.getByUserId(mcClane.getId()));
        // shoppingCartService.clear(shoppingCartMcClane);

        System.out.println("McClane's cart after clear: "
                + shoppingCartService.getByUserId(mcClane.getId()));
        shoppingCartService.deleteById(shoppingCartMcClane.getId());

        System.out.println(shoppingCartService.deleteById(8L));
        System.out.println(shoppingCartService.getAll());
        System.out.println("McClane shoping cart: "
                + shoppingCartService.getByUserId(alkapone.getId()));
        System.out.println("Update ");
        System.out.println();

        System.out.println("Test order");
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartAlkapone);
        orderService.completeOrder(shoppingCartAfonya);
        orderService.completeOrder(shoppingCartService.getByUserId(afonya.getId()));
        System.out.println("Alkapone order: " + orderService.getUserOrders(alkapone.getId()));
        orderService.deleteById(afonya.getId());
        System.out.println(orderService.getAll());
        for (Order order : orderService.getAll()) {
            System.out.println(order);
        }
        System.out.println("end)");
    }
}
