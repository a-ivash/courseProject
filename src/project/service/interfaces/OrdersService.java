package project.service.interfaces;

import project.model.orders.Order;
import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.List;

public interface OrdersService {
    /**
     * @param subscriber
     * @return list of orders for specified subscriber
     * @throws SQLException
     */
    List<Order> getOrdersForSubscriber(Subscriber subscriber) throws SQLException;

    /**
     * Creates service order for subscriber
     * @param serviceId
     * @param subscriberId
     * @throws SQLException
     */
    void createOrderForService(long serviceId, long subscriberId) throws SQLException;
}
