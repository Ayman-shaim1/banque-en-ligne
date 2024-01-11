package web;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        if(!uri.equals("/banque_war_exploded/login")) {
            // Check if the user is logged in (you may customize this check based on your authentication logic)
            boolean isLoggedIn = Auth.checkLoginStatus(httpRequest);
            if (isLoggedIn) {
                // User is logged in, continue with the request
                request.setAttribute("client",Auth.getLoggedClient(httpRequest));
                chain.doFilter(request, response);
            } else {
                // User is not logged in, redirect to the login page
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            }
        }
        else {
            chain.doFilter(request, response);
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
