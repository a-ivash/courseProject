package project.service.interfaces;

import project.model.services.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceService {
    /**
     * Saves service to database
     * @param service
     * @return service with updated ID field
     * @throws SQLException
     */
    Service saveService(Service service) throws SQLException;

    /**
     * Sets isActive field of service instance to false
     * @param service
     * @throws SQLException
     */
    void deactivateService(Service service) throws SQLException;

    /**
     * Updates name, description, price, payment type of service
     * @param service
     * @return updated service
     * @throws SQLException
     */
    Service updateService(Service service) throws SQLException;

    List<Service> getServices() throws SQLException;

    Service getService(long serviceId) throws SQLException;

}
