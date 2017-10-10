package project.command.common;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.ResourceBundleReader;
import project.filters.AnonymousAccessFilter;
import project.model.orders.Payment;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * This class obtains information about specified payment.
 * */
public class PaymentDetailsCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(PaymentDetailsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            long paymentId = Long.valueOf(request.getParameter("paymentId"));
            Payment payment = getPayment(paymentId);
            request.setAttribute("payment", payment);
        } catch(NumberFormatException e) {
            logger.error("Error while parsing number: " + request.getParameter("paymentId"));
            throw e;
        } catch (SQLException e) {
            logger.error("Error getting payments: " + e);
            String errorGettingPaymentMessage = ResourceBundleReader.getInstance().getProperty("paymentDetailsPage.errorGettingPaymentMessage");
            request.setAttribute("errorGettingPaymentMessage", errorGettingPaymentMessage);
        }
        return JspMap.PAYMENT_DETAILS;
    }

    private Payment getPayment(long paymentId) throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getPaymentService().getPayment(paymentId);
    }
}
