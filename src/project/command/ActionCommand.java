package project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface define method that all commands should implement.
 * @author Oleksii Ivashchenko
 * */
public interface ActionCommand {
    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return path to URI or to JSP page
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
