package project.command.common;

import project.command.ActionCommand;
import project.command.utils.HttpSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** This class changes system's language, setting it to session and redirects to page where request was made. */
public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter("lang");
        HttpSessionUtils.setLanguageToSession(request, language);
        String redirectTo = request.getParameter("redirectTo");
        return redirectTo;
    }
}
