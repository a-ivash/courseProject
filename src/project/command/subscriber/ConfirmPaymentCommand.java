package project.command.subscriber;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.model.orders.Payment;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.PaymentService;
import project.servlet.maps.UrlMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ConfirmPaymentCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(ConfirmPaymentCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            long paymentId = Long.parseLong(request.getParameter("paymentId"));
            confirmPayment(paymentId);
            String successConfirmPaymentMessage = ResourceBundleReader.getInstance().getProperty("paymentsPage.successPaymentConfirmation");
            request.setAttribute("successConfirmPaymentMessage", successConfirmPaymentMessage);

        } catch (NumberFormatException e) {
            logger.error("Error parsing number: " + request.getParameter("paymentId"));
            throw e;
        } catch (SQLException e) {
            String errorConfirmingPaymentMessage = ResourceBundleReader.getInstance().getProperty("paymentsPage.errorConfirmingPayment");
            request.setAttribute("errorConfirmingPaymentMessage", errorConfirmingPaymentMessage);
            logger.error("Error confirming payment: " + e);
        }
        return UrlMap.PAYMENTS;
    }

    private void confirmPayment(long paymentId) throws SQLException {
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        PaymentService paymentService = serviceFactory.getPaymentService();
        Payment payment = paymentService.getPayment(paymentId);
        paymentService.confirmPayment(payment);
    }
}
