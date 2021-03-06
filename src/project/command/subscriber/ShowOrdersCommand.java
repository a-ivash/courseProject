package project.command.subscriber;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.command.utils.ResourceBundleReader;
import project.model.orders.Order;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowOrdersCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(ShowOrdersCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Subscriber subscriber = HttpSessionUtils.getSubscriber(request);
            List<Order> orders = getOrdersForSubscriber(subscriber);
            request.setAttribute("orders", orders);
        } catch (SQLException e) {
            String errorGettingOrdersMessage = ResourceBundleReader.getInstance().getProperty("subscriberOrders.errorGettingOrders");
            request.setAttribute("errorGettingOrdersMessage", errorGettingOrdersMessage);
            logger.error("Error while getting orders: " + e);
        }
        return JspMap.ORDERS_SUBSCRIBER;
    }

    private List<Order> getOrdersForSubscriber(Subscriber subscriber) throws SQLException{
        return AbstractServiceFactory.getDefaultFactory().getOrdersService().getOrdersForSubscriber(subscriber);
    }
}
