package project.command.common;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.command.utils.ResourceBundleReader;
import project.filters.AnonymousAccessFilter;
import project.model.orders.Payment;
import project.model.users.AbstractUser;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/** This class obtains information about payments both for admin's and subscriber's view.*/
public class PaymentListCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(PaymentListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            AbstractUser abstractUser = HttpSessionUtils.getAbstractUser(request);
            if (abstractUser.isAdministrator()) {
                setPaymentsToRequest(request);
            } else {
                setSubscriberPaymentsToRequest(request, (Subscriber)abstractUser);
            }
        } catch (SQLException e) {
            logger.error("Error getting payments: " + e);
            String errorGettingPaymentsMessage = ResourceBundleReader.getInstance().getProperty("paymentsPage.errorGettingPayments");
            request.setAttribute("errorGettingPaymentsMessage",  errorGettingPaymentsMessage);
        }
        return JspMap.PAYMENTS;
    }

    /** Setting all payments to request in admin dashboard*/
    private void setPaymentsToRequest(HttpServletRequest request) throws SQLException{
        request.setAttribute("payments", getPayments());
    }

    /** Showing only specified subscriber's payments. */
    private void setSubscriberPaymentsToRequest(HttpServletRequest request, Subscriber subscriber) throws SQLException{
        List<Payment> payments = getSubscribersPayments(subscriber);
        request.setAttribute("payments", payments);
    }

    private List<Payment> getSubscribersPayments(Subscriber subscriber) throws SQLException{
        return AbstractServiceFactory.getDefaultFactory().getPaymentService().getSubscribersPayments(subscriber);
    }

    /** Returns all payments from database. */
    private List<Payment> getPayments() throws SQLException {
        return AbstractServiceFactory.getDefaultFactory().getPaymentService().getPayments();
    }
}
