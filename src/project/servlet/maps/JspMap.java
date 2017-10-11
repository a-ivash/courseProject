package project.servlet.maps;

/**
 * This interface simply contains constants with paths to JSP pages.
 * @author Oleksii Ivashchenko
 * @version 1.0
 */
public interface JspMap {
    String LOGIN = "/public/login.jsp";
    String INDEX = "/index.jsp";
    String CREATE_ACCOUNT = "/public/createAccount.jsp";
    String REGISTRATION_CONFIRM = "/public/registrationConfirm.jsp";
    String DASHBOARD_ADMIN = "/admin/dashboard.jsp";
    String DASHBOARD_SUBSCRIBER = "/subscriber/dashboard.jsp";
    String DASHBOARD_REDIRECT = "/common/dashboardRedirect.jsp";
    String SUBSCRIBERS = "/admin/subscribers.jsp";
    String SUBSCRIBER_DETAILS = "/admin/subscriberDetails.jsp";
    String CREATE_SERVICE = "/admin/createService.jsp";
    String ORDERS_SUBSCRIBER = "/subscriber/orders.jsp";
    String PAYMENTS = "/common/payments.jsp";
    String PAYMENT_DETAILS = "/common/paymentDetails.jsp";
    String SERVICES = "/common/services.jsp";
    String SERVICE_DETAILS = "/common/serviceDetails.jsp";

}
