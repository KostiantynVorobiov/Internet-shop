package com.internet.shop.web.filters;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private Map<String, Set<Role.RoleName>> protectedUrs = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrs.put("/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrs.put("/users/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrs.put("/products/add", Set.of(Role.RoleName.ADMIN));
        protectedUrs.put("/products/manage", Set.of(Role.RoleName.ADMIN));
        protectedUrs.put("/products/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrs.put("/orders", Set.of(Role.RoleName.ADMIN));
        protectedUrs.put("/orders/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrs.put("/shopping-carts/products/add", Set.of(Role.RoleName.USER));
        protectedUrs.put("/shopping-carts/products", Set.of(Role.RoleName.USER));
        protectedUrs.put("/shopping-cart/products/delete", Set.of(Role.RoleName.USER));
        protectedUrs.put("/user/orders", Set.of(Role.RoleName.USER));
        protectedUrs.put("/order/create", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUrl = request.getServletPath();
        if (protectedUrs.get(requestUrl) == null) {
            filterChain.doFilter(request, response);
            return;
        }
        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        User user = userService.getById(userId);
        if (isAuthorized(user, protectedUrs.get(requestUrl))) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp")
                    .forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
