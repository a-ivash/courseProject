package project.command.subscriber;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.command.utils.ResourceBundleReader;
import project.database.exceptions.EmailAlreadyTakenException;
import project.model.users.Address;
import project.model.users.Subscriber;
import project.service.interfaces.AbstractServiceFactory;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/** This class processes create account form and adds new subscriber to system. It also redirects user to confirmation page
 *  where the generated password is shown.
 *  It checks if email entered by user is already in system. */
public class CreateAccountCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(CreateAccountCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Subscriber subscriber = parseSubscriber(request);
        try {
            subscriber = saveSubscriber(subscriber);
            HttpSessionUtils.addUserToSession(subscriber, request);
            String generatedPassword = subscriber.getRawPassword();
            request.setAttribute("generatedPassword", generatedPassword);
        } catch (EmailAlreadyTakenException e) {
            String emailAlreadyInUse = ResourceBundleReader.getInstance().getProperty("createAccountPage.emailAlreadyInUse");
            request.setAttribute("emailAlreadyInUse", emailAlreadyInUse);
            logger.info("Trying to create account with existing email: " + subscriber.getEmail());
            return JspMap.CREATE_ACCOUNT;
        } catch (SQLException e) {
            String errorCreateAccountMessage = ResourceBundleReader.getInstance().getProperty("createAccountPage.errorCreateAccount");
            request.setAttribute("errorCreateAccountMessage", errorCreateAccountMessage);
            logger.error("Error creating account: " + e);
            return JspMap.CREATE_ACCOUNT;
        }
        return JspMap.REGISTRATION_CONFIRM;
    }

    private Subscriber saveSubscriber(Subscriber subscriber) throws SQLException{
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        return serviceFactory.getCreateAccountService().saveSubscriber(subscriber);
    }

    private Subscriber parseSubscriber(HttpServletRequest request) {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName(request.getParameter("firstName"));
        subscriber.setLastName(request.getParameter("lastName"));
        subscriber.setEmail(request.getParameter("email"));
        subscriber.setJoinDate(null);
        subscriber.setAddress(parseAddress(request));
        return subscriber;
    }

    private Address parseAddress(HttpServletRequest request) {
        Address address = new Address();
        address.setStreetName(request.getParameter("street"));
        address.setBuildingNumber(request.getParameter("building"));
        address.setApartmentsNumber(request.getParameter("apartments"));
        return address;
    }
}
