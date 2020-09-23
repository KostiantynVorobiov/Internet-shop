package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.UserService;

import java.util.Set;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        System.out.println("Test product");
//        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
//        Product honor = new Product("Honor", 5362.52);
//        Product huawei = new Product("Huawei", 4569.25);
//        Product xiaomi = new Product("Xiaomi", 3245.10);
//        productService.create(huawei);
//        productService.create(honor);
//        productService.create(xiaomi);
//        System.out.println("Get product id " + honor.getId());
//        System.out.println("Get product by id " + productService.getById(honor.getId()));
//        System.out.println(productService.getAll());
//        xiaomi.setPrice(xiaomi.getPrice() + 10000);
//        System.out.println("Update product " + productService.update(xiaomi));
//        System.out.println(productService.getAll());
//        productService.deleteById(xiaomi.getId());
//        System.out.println("After delete " + productService.getAll());

        System.out.println("Test users");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        User alkapone = new User("Alkapone", "Alkaponchil_2008", "12346789");
        User mcClane = new User("McClane", "oreshek.2008", "911911");
        User afonya = new User("Afonya", "golubi.1978", "1111");
        User afanasii = new User("Afanasii", "Ivanov.2020", "13579");
        alkapone.setRoles(Set.of(Role.of("USER")));
        mcClane.setRoles(Set.of(Role.of("ADMIN")));
        afonya.setRoles(Set.of(Role.of("USER")));
        afanasii.setRoles(Set.of(Role.of("USER")));
        userService.create(alkapone);
        userService.create(mcClane);
        userService.create(afonya);
        userService.create(afanasii);
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
    }
}
