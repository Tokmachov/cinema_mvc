package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/movies/*"})
public class UserLoginFilter implements Filter {
    public static final String ORIGINAL_REQUEST_URI_KEY = "originalRequestUriKey";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
        if (isRegisteredUser(cookies)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.getServletContext().setAttribute(ORIGINAL_REQUEST_URI_KEY, req.getRequestURI());
        resp.sendRedirect(req.getContextPath() + "/user/login");
    }

    private boolean isRegisteredUser(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String key = cookie.getName();
                if (key.equals("user")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}