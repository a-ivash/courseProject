package project.filters;

import project.command.utils.HttpSessionUtils;
import project.model.users.AbstractUser;
import project.servlet.maps.JspMap;
import project.servlet.maps.UrlMap;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Checks if requesting url belongs to admin privileges and current user is admin.
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class AdminAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (checkRequestForAdminAccess(httpRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpRequest.getRequestDispatcher(JspMap.LOGIN).forward(servletRequest, servletResponse);
        }
    }

    private boolean checkRequestForAdminAccess(HttpServletRequest request) {
        AbstractUser user = HttpSessionUtils.getAbstractUser(request);
        String url = request.getServletPath();
        if (url.startsWith("/admin") && user.isAdministrator()) {
            return true;
        }

        switch(url) {
            case UrlMap.SUBSCRIBERS:
            case UrlMap.SUBSCRIBER_RAW:
            case JspMap.CREATE_SERVICE:
            case JspMap.SUBSCRIBERS:
            case JspMap.SUBSCRIBER_DETAILS: return user.isAdministrator();
            default: return false;
        }
    }

    @Override
    public void destroy() {

    }
}
