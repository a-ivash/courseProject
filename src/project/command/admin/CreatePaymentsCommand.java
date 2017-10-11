package project.command.admin;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.CreatePaymentsService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * This class processes admin's request for forming payments for the last month.
 * */
public class CreatePaymentsCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(CreatePaymentsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            formPaymentsForLastMonth();
            String successPaymentsFormMessage = ResourceBundleReader.getInstance().getProperty("paymentsPage.successPaymentsForm");
            request.setAttribute("successPaymentsFormMessage",  successPaymentsFormMessage);
        } catch (SQLException e) {
            String errorCreatePaymentsMessage = ResourceBundleReader.getInstance().getProperty("paymentsPage.errorCreatePayments");
            request.setAttribute("errorCreatePaymentsMessage", errorCreatePaymentsMessage);
            logger.error("Error while creating payments: " + e);
        }
        return UrlMap.PAYMENTS;
    }

    private void formPaymentsForLastMonth() throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        CreatePaymentsService paymentsService = serviceFactory.getCreatePaymentsService();
        paymentsService.formServicesForLastMonth();
    }
}
