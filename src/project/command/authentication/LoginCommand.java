package project.command.authentication;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.command.utils.ResourceBundleReader;
import project.database.exceptions.WrongEmailPasswordException;
import project.model.users.AbstractUser;
import project.service.interfaces.AbstractServiceFactory;
import project.service.interfaces.LoginService;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * This command processes login request and if success returns the dashboard view for user's role (admin / subscriber).
 * Otherwise - redirect to login page.
 * */
public class LoginCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        try {
            AbstractUser abstractUser = login(userEmail, userPassword);
            HttpSessionUtils.addUserToSession(abstractUser, request);
            return JspMap.DASHBOARD_REDIRECT;
        } catch (WrongEmailPasswordException e) {
            String wrongEmailPasswordMessage = ResourceBundleReader.getInstance().getProperty("loginPage.emailPasswordIncorrect");
            request.setAttribute("wrongEmailPasswordMessage", wrongEmailPasswordMessage);
            logger.error("Trying to enter the system with wrong credentials: " + userEmail);
            return JspMap.LOGIN;
        } catch (SQLException e) {
            logger.error("Error while login processing: " + e);
            return JspMap.LOGIN;
        }
    }

    private AbstractUser login(String email, String password) throws SQLException, WrongEmailPasswordException{
        AbstractServiceFactory serviceFactory = AbstractServiceFactory.getDefaultFactory();
        LoginService loginService = serviceFactory.getLoginService();
        return loginService.login(email, password);
    }
}
