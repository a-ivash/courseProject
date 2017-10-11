package project.service.interfaces;

import project.service.implementation.DefaultServiceFactory;

/**
 * Define a basic interface all ServiceFactories must implement.
 * @author Oleksii Ivashchenko
 * @version 1.0
 */
public interface AbstractServiceFactory {
    LoginService getLoginService();
    CreateAccountService getCreateAccountService();
    CreatePaymentsService getCreatePaymentsService();
    ServiceService getServiceService();
    SubscriberService getSubscriberService();
    OrdersService getOrdersService();
    PaymentService getPaymentService();

    static AbstractServiceFactory getDefaultFactory() {
        return new DefaultServiceFactory();
    }
}
