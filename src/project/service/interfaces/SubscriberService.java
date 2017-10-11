package project.service.interfaces;

import project.model.users.Subscriber;

import java.sql.SQLException;
import java.util.List;

public interface SubscriberService {
    /**
     * Activates subscriber by setting isActive = true AND setting phone number if any not set before
     * @param subscriberId
     * @return activated user with phoneNumber field referring to Phone instance.
     * @throws SQLException
     */
    Subscriber activateSubscriber(long subscriberId) throws SQLException;

    /**
     * Setting subscriber's isActive field to false
     * @param subscriber
     * @throws SQLException
     */
    void blockSubscriber(Subscriber subscriber) throws SQLException;

    List<Subscriber> getSubscribers() throws SQLException;

    Subscriber getSubscriber(long subscriberId) throws SQLException;
}
