package project.command.admin;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.filters.AnonymousAccessFilter;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * This class processes request for getting the list of all subscribers registered in system.
 * */
public class SubscribersListCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(SubscribersListCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        try {
            List<Subscriber> subscribers = getSubscribersList();
            request.setAttribute("subscribers", subscribers);
        } catch (SQLException e) {
            String errorGettingSubscribersMessage = ResourceBundleReader.getInstance().getProperty("subscribersPage.errorGettingSubscribersMessage");
            request.setAttribute("errorGettingSubscribersMessage", errorGettingSubscribersMessage);
            logger.error("Error getting list of subscribers.");
        }
        return JspMap.SUBSCRIBERS;
    }

    private List<Subscriber> getSubscribersList() throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getSubscriberService().getSubscribers();
    }
}
