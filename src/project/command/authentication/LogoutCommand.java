package project.command.authentication;

import org.apache.log4j.Logger;
import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;
import project.filters.AnonymousAccessFilter;
import project.servlet.maps.JspMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** This class simply invalidates session if any exists. */
public class LogoutCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(LogoutCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            logger.info("User is logged out: " + HttpSessionUtils.getAbstractUser(request).toString());
            session.invalidate();
        }
        return JspMap.INDEX;
    }
}
