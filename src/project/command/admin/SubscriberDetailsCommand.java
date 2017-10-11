package project.command.admin;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * This class retrieves information about specified subscriber.
 * */
public class SubscriberDetailsCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(SubscriberDetailsCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            long subscriberId = Long.valueOf(request.getParameter("subscriberId"));
            Subscriber subscriber = getSubscriber(subscriberId);
            request.setAttribute("subscriber", subscriber);
        } catch (NumberFormatException e) {
            logger.error("Error while parsing number: " + request.getParameter("subscriberId"));
            throw e;
        } catch (SQLException e) {
            String errorGettingSubscriberMessage = ResourceBundleReader.getInstance().getProperty("subscribersDetailsPage.errorGettingSubscriber");
            request.setAttribute("errorGettingSubscriberMessage", errorGettingSubscriberMessage);
            logger.error("Error while getting subscriber's information: " + e);
        }
        return JspMap.SUBSCRIBER_DETAILS;
    }

    private Subscriber getSubscriber(long subscriberId) throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getSubscriberService().getSubscriber(subscriberId);
    }
}
