package project.servlet.maps;

public interface UrlMap {
    String SUBSCRIBERS = "/subscribers";
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
