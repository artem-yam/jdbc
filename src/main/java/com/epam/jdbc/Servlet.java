package com.epam.jdbc;

import com.epam.jdbc.command.ActionCommand;
import com.epam.jdbc.command.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple servlet
 */
public class Servlet extends HttpServlet {

    /**
     * Servlet serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(req);

        GoToPageHandler pageHandler = command.execute(req);

        if (pageHandler != null) {

            GoToPageMethodEnum method = pageHandler.getMethod();

            switch (method) {
                case FORWARD:
                    RequestDispatcher dispatcher = getServletContext()
                            .getRequestDispatcher(
                                    pageHandler
                                            .getPage());
                    dispatcher.forward(req, resp);
                    break;
                case REDIRECT:
                    resp.sendRedirect(
                            req.getContextPath() + pageHandler.getPage());
                    break;
                default:
                    break;
            }
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }

}