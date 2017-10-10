package project.command.subscriber;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.command.utils.ResourceBundleReader;
import project.filters.AnonymousAccessFilter;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class MakeOrderCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(MakeOrderCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            long serviceId = Long.parseLong(request.getParameter("serviceId"));
            long subscriberId = HttpSessionUtils.getSubscriber(request).getId();
            createOrderForService(serviceId, subscriberId);

            String successMakeOrderMessage = ResourceBundleReader.getInstance().getProperty("subscriberOrders.successMakeOrder");
            request.setAttribute("successMakeOrderMessage", successMakeOrderMessage);

        } catch (NumberFormatException e) {
            logger.error("Error parsing number: " + request.getParameter("serviceId"));
            throw e;
        } catch (SQLException e) {
            logger.error("Error while making order: " + e);
            String errorMakingOrderMessage = ResourceBundleReader.getInstance().getProperty("servicesPage.errorMakingOrder");
            request.setAttribute("errorMakingOrderMessage", errorMakingOrderMessage);
        }

        return UrlMap.ORDERS;
    }

    private void createOrderForService(long serviceId, long subscriberId) throws SQLException {
        AbstractServiceFactory.getDefaultFactory().getOrdersService().createOrderForService(serviceId, subscriberId);
    }
}
