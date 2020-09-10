package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User mcClane = new User("McClane", "oreshek.2008", "911911");
        User afonya = new User("Afonya", "golubi.1978", "1111");
        User afanasii = new User("Afanasii", "Ivanov.2020", "13579");
        userService.create(mcClane);
        userService.create(afonya);
        userService.create(afanasii);

        Product motorola = new Product("Motorola", 50.50);
        Product samsung = new Product("Samsung", 7000.55);
        Product honor = new Product("Honor", 5256.85);
        productService.create(samsung);
        productService.create(honor);
        productService.create(motorola);

        ShoppingCart shoppingCartAffanasii = new ShoppingCart(afanasii.getId());
        ShoppingCart shoppingCartAfonya = new ShoppingCart(afonya.getId());
        ShoppingCart shoppingCartMcClane = new ShoppingCart(mcClane.getId());
        shoppingCartService.create(shoppingCartAffanasii);
        shoppingCartService.create(shoppingCartAfonya);
        shoppingCartService.create(shoppingCartMcClane);
        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
