package project.filters;

import project.command.utils.HttpSessionUtils;
import project.command.utils.ResourceBundleReader;
import project.database.dao.factories.AbstractDAOFactory;
import project.model.users.AbstractUser;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.SubscriberService;
import project.servlet.maps.JspMap;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class InactiveUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        AbstractUser user = HttpSessionUtils.getAbstractUser(httpServletRequest);
        if (user != null && !user.isAdministrator()) {
            try {
                Subscriber subscriber = getSubscriber((Subscriber) user);
                boolean restrictAccess = !subscriber.isActive() || subscriber.isBlocked();

                if (subscriber.isBlocked()) {
                    String blockedUserMessage = ResourceBundleReader.getInstance().getProperty("subscriberDashboard.blockedAccountMessage");
                    httpServletRequest.setAttribute("BLOCKED_USER_MESSAGE", blockedUserMessage);
                } else if (!subscriber.isActive()) {
                    String inactiveUserMessage = ResourceBundleReader.getInstance().getProperty("subscriberDashboard.inactiveAccountMessage");
                    httpServletRequest.setAttribute("INACTIVE_USER_MESSAGE", inactiveUserMessage);
                }


                if (restrictAccess) {
                    httpServletRequest.getRequestDispatcher(JspMap.DASHBOARD_SUBSCRIBER).forward(servletRequest, servletResponse);
                    return;
                }
            } catch (SQLException e) {}
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Subscriber getSubscriber(Subscriber subscriber) throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        SubscriberService subscriberService = serviceFactory.getSubscriberService();
        return subscriberService.getSubscriber(subscriber.getId());
    }

    @Override
    public void destroy() {

    }
}
