package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product honor = new Product("Honor", 5362.52);
        Product huawei = new Product("Huawei", 4569.25);
        Product xiaomi = new Product("Xiaomi", 3245.10);
        Product motorola = new Product("Motorola", 50.50);
        productService.create(huawei);
        productService.create(honor);
        productService.create(xiaomi);
        System.out.println("Get product " + honor.getId());
        System.out.println("Get product by id " + productService.getById(honor.getId()));
        System.out.println(productService.getAll());
        xiaomi.setId(xiaomi.getId() + 2);
        System.out.println("Update product " + productService.update(xiaomi));
        System.out.println(productService.getAll());
        productService.deleteById(xiaomi.getId());
        System.out.println(productService.getAll());
    }
}
