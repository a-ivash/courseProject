package project.command.admin;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.SubscriberService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * This class processes admins request for blocking subscriber.
 * */
public class BlockSubscriberCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(BlockSubscriberCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long subscriberId = 0;
        try {
            subscriberId = Long.parseLong(request.getParameter("subscriberId"));
            blockSubscriber(subscriberId);
            String successBlockedMessage = ResourceBundleReader.getInstance().getProperty("subscribersDetailsPage.successBlocked");
            request.setAttribute("successBlockedMessage", successBlockedMessage);

            logger.info(String.format("Subscriber id#%d is blocked!", subscriberId));
        } catch (NumberFormatException e) {
            logger.error("Trying to parse number: " + request.getParameter("subscriberId"));
            throw e;
        } catch (SQLException e) {
            String errorBlockingMessage = ResourceBundleReader.getInstance().getProperty("subscribersDetailsPage.errorBlocking");
            request.setAttribute("errorBlockingMessage", errorBlockingMessage);

            logger.error("Error while blocking subscriber: " + e);
        }
        return String.format(UrlMap.SUBSCRIBER, subscriberId);
    }

    private void blockSubscriber(long subscriberId) throws SQLException{
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        SubscriberService subscriberService = serviceFactory.getSubscriberService();
        Subscriber subscriber = subscriberService.getSubscriber(subscriberId);
        subscriberService.blockSubscriber(subscriber);
    }
}
