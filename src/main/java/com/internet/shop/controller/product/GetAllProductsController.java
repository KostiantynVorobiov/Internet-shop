package com.internet.shop.controller.product;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllProductsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> allProducts = productService.getAll();
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/views/product/all.jsp").forward(req, resp);
    }
}
