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
import java.util.List;

/** This class obtains information about all services in phone system*/
public class ServiceListCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(ServiceListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Service> services = getServices();
            request.setAttribute("services", services);
        } catch (SQLException e) {
            String errorGettingServicesMessage = ResourceBundleReader.getInstance().getProperty("servicesPage.errorGettingServices");
            request.setAttribute("errorGettingServicesMessage", errorGettingServicesMessage);
            logger.error("Error getting list of services: " + e);
        }
        return JspMap.SERVICES;
    }

    /**
     * @return List of active services
     * */
    private List<Service> getServices() throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getServiceService().getServices();
    }
}
