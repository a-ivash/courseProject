package project.command.admin;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.filters.AnonymousAccessFilter;
import project.model.services.PaymentType;
import project.model.services.Service;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.ServiceService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateServiceCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(CreateServiceCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Service service = parseService(request);
        try {
            saveService(service);
            String successCreateServiceMessage = ResourceBundleReader.getInstance().getProperty("servicesPage.successCreateService");
            request.setAttribute("successCreateServiceMessage", successCreateServiceMessage);
        } catch (SQLException e) {
            logger.error("Error while creating service: " + e);
            String errorCreateServiceMessage = ResourceBundleReader.getInstance().getProperty("createServicePage.errorCreateService");
            request.setAttribute("errorCreateServiceMessage", errorCreateServiceMessage);
        }

        return UrlMap.SERVICES;
    }

    private Service saveService(Service service) throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        ServiceService serviceService = serviceFactory.getServiceService();
        return serviceService.saveService(service);
    }

    private Service parseService(HttpServletRequest request) {
        Service service = new Service();
        service.setServiceName(request.getParameter("serviceName"));
        service.setServiceDescription(request.getParameter("serviceDescription"));
        service.setServicePrice(Double.valueOf(request.getParameter("servicePrice")));
        PaymentType paymentType = PaymentType.valueOf(request.getParameter("paymentType"));
        service.setPaymentType(paymentType);
        return service;
    }
}
