package project.command.admin;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.model.services.Service;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.ServiceService;
import project.servlet.maps.JspMap;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateServiceCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(UpdateServiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long serviceId = 0;
        try {
            serviceId = Long.valueOf(request.getParameter("serviceId"));
            String action = request.getParameter("action");
            if (action.equals("edit")) {
                return processShowForm(request, serviceId);
            }
            if (action.equals("submit")) {
                return processSubmitForm(request, serviceId);
            }
        } catch (NumberFormatException e) {
            logger.error("Error while parsing number: " + request.getParameter("serviceId"));
            throw e;
        } catch (SQLException e) {
            logger.error("Error while updating service: " + e);
            // add error message
        }
        return String.format(UrlMap.SERVICE, serviceId);
    }

    private String processShowForm(HttpServletRequest request, long serviceId) throws SQLException {
        Service service = AbstractServiceFactory.getDefaultFactory().getServiceService().getService(serviceId);
        request.setAttribute("service", service);
        return JspMap.CREATE_SERVICE;
    }

    private String processSubmitForm(HttpServletRequest request, long serviceId) throws SQLException {
        Service service = parseService(request);
        Service updatedService = updateService(serviceId, service);
        request.setAttribute("service", updatedService);
        return String.format(UrlMap.SERVICE, serviceId);
    }

    private Service updateService(long serviceId, Service service) throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        ServiceService serviceService = serviceFactory.getServiceService();
        service.setId(serviceId);
        serviceService.updateService(service);
        return service;
    }


    private Service parseService(HttpServletRequest request) {
        Service service = new Service();
        service.setServiceName(request.getParameter("serviceName"));
        service.setServiceDescription(request.getParameter("serviceDescription"));
        service.setServicePrice(Double.parseDouble(request.getParameter("servicePrice")));
        return service;
    }
}
