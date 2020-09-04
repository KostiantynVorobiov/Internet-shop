package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        Product motorola = new Product("Motorola", 50.50);
        Product samsung = new Product("Samsung", 7000.55);
        Product honor = new Product("Honor", 5256.85);
        Product huawei = new Product("Huawei", 4569.25);
        Product xiaomi = new Product("Xiaomi", 3245.10);
        Product nokia = new Product("Nokia", 6215.99);
        productService.create(samsung);
        productService.create(honor);
        productService.create(huawei);
        productService.create(xiaomi);
        productService.create(nokia);
        productService.create(motorola);
        System.out.println(productService.getAll());
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        System.out.println();

        System.out.println("Read product by ID " + productService.get(huawei.getId()));

        motorola.setPrice(1000.00);
        motorola.setName("LG");
        System.out.println("Product after update " + productService.update(motorola));
        System.out.println();

        productService.delete(honor.getId());
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
    }
}
