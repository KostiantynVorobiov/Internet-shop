package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.Role;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.Set;
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
        mcClane.setRoles(Set.of(Role.of("USER")));
        userService.create(mcClane);
        User afonya = new User("Afonya", "golubi.1978", "1111");
        afonya.setRoles(Set.of(Role.of("USER")));
        userService.create(afonya);
        User afanasii = new User("Afanasii", "Ivanov.2020", "13579");
        afanasii.setRoles(Set.of(Role.of("USER")));
        userService.create(afanasii);

        User admin = new User("Admin", "admin", "1");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);

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
