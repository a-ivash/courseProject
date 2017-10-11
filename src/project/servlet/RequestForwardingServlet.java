package project.servlet;

import project.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class handles request from user and processes it with a suitable request command.
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class RequestForwardingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Processes the request both for post and get requests.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();

        ActionCommand executedCommand = CommandMap.getCommand(url);
        String path = executedCommand.execute(request, response);
        getServletContext().getRequestDispatcher(path).forward(request, response);
    }
}
