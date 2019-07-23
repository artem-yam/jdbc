package com.epam.jdbc.servlet;

import com.epam.jdbc.command.ActionCommand;
import com.epam.jdbc.command.ActionFactory;
import com.epam.jdbc.command.dto.TransitionInformation;
import com.epam.jdbc.command.dto.TransitionMethod;
import com.epam.jdbc.command.parameters.CommandParameters;
import com.epam.jdbc.command.parameters.ParametersFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple servlet
 */
public class FrontController extends HttpServlet {

    /**
     * FrontController serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ActionCommand command = new ActionFactory().defineCommand(req);


        TransitionInformation pageHandler = null;

        try {
            CommandParameters commandParams = new ParametersFactory(command)
                    .defineParameters(req);

            pageHandler = command.execute(commandParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        if (pageHandler != null) {

            TransitionMethod method = pageHandler.getMethod();

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