package mum.wap.servlet;

import mum.wap.domain.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletFilter implements Filter {

    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        System.out.println(uri);
        if(uri.endsWith("/login")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {

            ServletHelper helper = new ServletHelper();
            User loggedUser = (User) req.getSession().getAttribute("user");
            if (loggedUser == null) {
                System.out.println("Unauthorized access request");
                HttpServletResponse resp = (HttpServletResponse) servletResponse;
                //resp.sendRedirect("http://localhost:8080/lab-jsp-servlet/login");
                resp.sendRedirect("/lab-jsp-servlet/login");
               // filterChain.doFilter(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
