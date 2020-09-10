package com.internet.shop.controllers;

import java.io.IOException;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddProductController  extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        Double price = Double.valueOf(req.getParameter("price"));
        Product product = new Product(name, price);
        productService.create(product);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
