package project.command.admin;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.model.services.Service;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.ServiceService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/** This class sets command's status as deactivated (i.e. not possibly to order)*/
public class DeactivateServiceCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(DeactivateServiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long serviceId = 0;
        try {
            serviceId = Long.valueOf(request.getParameter("serviceId"));
            deactivateService(serviceId);
            logger.info(String.format("Service #%d was deactivated", serviceId));
            return UrlMap.SERVICES;
        } catch (NumberFormatException e) {
            logger.error("Error parsing number: " + request.getParameter("serviceId"));
            throw e;
        } catch (SQLException e) {
            String errorDeactivateServiceMessage = ResourceBundleReader.getInstance().getProperty("serviceDetailsPage.errorDeactivatingService");
            request.setAttribute("errorDeactivateServiceMessage", errorDeactivateServiceMessage);
            logger.error("Error while deactivating service: " + e);
        }
        return String.format(UrlMap.SERVICE, serviceId);
    }

    private void deactivateService(long serviceId) throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        ServiceService serviceService = serviceFactory.getServiceService();
        Service service = serviceService.getService(serviceId);
        serviceService.deactivateService(service);
    }
}
