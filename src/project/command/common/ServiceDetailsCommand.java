package project.command.common;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.filters.AnonymousAccessFilter;
import project.model.services.Service;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/** This class obtains the list of services and returns to user's view. */
public class ServiceDetailsCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(AnonymousAccessFilter.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            long serviceId = Long.valueOf(request.getParameter("serviceId"));
            Service service = getService(serviceId);
            request.setAttribute("service", service);
        } catch (NumberFormatException e) {
            logger.error("Error parsing number: " + request.getParameter("serviceId"));
            throw e;
        } catch (SQLException e) {
            String errorGettingServiceMessage = ResourceBundleReader.getInstance().getProperty("serviceDetailsPage.errorGettingService");
            request.setAttribute("errorGettingServiceMessage", errorGettingServiceMessage);
            logger.error("Error getting service details: " + e);
        }
        return JspMap.SERVICE_DETAILS;
    }

    private Service getService(long serviceId) throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getServiceService().getService(serviceId);
    }
}
