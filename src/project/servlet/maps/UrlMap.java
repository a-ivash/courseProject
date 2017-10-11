package project.servlet.maps;

/**
 * This interface simply contains constants with urls to specified resources.
 * @author Oleksii Ivashchenko
 * @version 1.0
 */
public interface UrlMap {
    String SUBSCRIBERS = "/subscribers";
    String SUBSCRIBER_RAW = "/subscriber";
    String SUBSCRIBER = "/subscriber?subscriberId=%d";
    String LOGIN = "/login";
    String REGISTER = "/register";
    String PAYMENTS = "/payments";
    String RESOURCES = "/resources";
    String INCLUDES = "/includes";
    String SERVICES = "/services";
    String SERVICE = "/service?serviceId=%d";
    String ORDERS = "/orders";
    String CHANGE_LANGUAGE = "/changeLanguage";
}
