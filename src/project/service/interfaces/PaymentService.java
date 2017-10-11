package project.service.interfaces;

import project.model.orders.Payment;
import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.List;

public interface PaymentService {
    /**
     * @param subscriber
     * @return list of specified subscriber payments
     * @throws SQLException
     */
    List<Payment> getSubscribersPayments(Subscriber subscriber) throws SQLException;

    /**
     * @return list of all payments in system
     * @throws SQLException
     */
    List<Payment> getPayments() throws SQLException;

    Payment getPayment(long paymentId) throws SQLException;

    /**
     * @param payment
     * @return payment confirmed by subscriber
     * @throws SQLException
     */
    Payment confirmPayment(Payment payment) throws SQLException;
}
