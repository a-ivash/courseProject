package project.command.admin;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.database.exceptions.NoAvailablePhonesException;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.SubscriberService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * This class processes admin queries to activate subscriber
 * (i.e. set his phone number AND/OR activating user account).
 * */
public class ActivateSubscriberCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(ActivateSubscriberCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long subscriberId = Long.valueOf(request.getParameter("subscriberId"));
        try {
            Subscriber subscriber = activateSubscriber(subscriberId);
            String successActivateMessage = ResourceBundleReader.getInstance().getProperty("subscribersDetailsPage.successActivated");
            request.setAttribute("successActivatedMessage", successActivateMessage);

            logger.info(String.format("Subscriber id#%d is activated", subscriberId));
        } catch(NoAvailablePhonesException e) {
            String noAvailablePhone = ResourceBundleReader.getInstance().getProperty("subscribersDetailsPage.noAvailablePhonesLeft");
            request.setAttribute("noAvailablePhonesMessage", noAvailablePhone);

            logger.info("No available phones left.");
        } catch (SQLException e) {
            String errorActivatingMessage = ResourceBundleReader.getInstance().getProperty("subscribersDetailsPage.errorActivating");
            request.setAttribute("errorActivatingMessage", errorActivatingMessage);

            logger.error("Error activating subscriber: " + e);
        }
        // returning to the same subscriber's page we made the request from
        return String.format(UrlMap.SUBSCRIBER, subscriberId);
    }

    private Subscriber activateSubscriber(long subscriberId) throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        SubscriberService activateSubscriberService = serviceFactory.getSubscriberService();
        return activateSubscriberService.activateSubscriber(subscriberId);
    }
}
