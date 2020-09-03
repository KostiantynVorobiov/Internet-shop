package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        Product productForUpdate = new Product("Motorola", 50.50);
        Product fistProduct = new Product("Samsung", 7000.55);
        productService.create(fistProduct);
        productService.create(new Product("Honor", 5256.85));
        productService.create(new Product("Huawei", 4569.25));
        productService.create(new Product("Xiaomi", 3245.10));
        productService.create(new Product("Nokia", 6215.99));
        productService.create(productForUpdate);
        System.out.println(productService.getAll());
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        System.out.println();

        System.out.println("Read product by ID " + productService.get(3L));

        productForUpdate.setPrice(1000.00);
        productForUpdate.setName("LG");
        System.out.println(productService.update(productForUpdate));
        System.out.println();

        productService.delete(2L);
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
    }
}
