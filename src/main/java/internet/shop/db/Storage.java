package internet.shop.db;

import internet.shop.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Long productId = 0L;
    public static final List<Product> products = new ArrayList<>();

    public static void addProduct (Product product) {
        productId++;
        product.setId(productId);
        products.add(product);
    }
}
