package com.epam.jdbc.servlet;

import com.epam.jdbc.command.ActionCommand;
import com.epam.jdbc.command.ActionFactory;
import com.epam.jdbc.command.dto.TransitionInformation;
import com.epam.jdbc.command.dto.TransitionMethod;
import com.epam.jdbc.command.parameters.CommandParameters;
import com.epam.jdbc.command.parameters.ParametersFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Simple servlet
 */
public class FrontController extends HttpServlet {
    
    /**
     * FrontController serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Logger
     */
    private static final Logger logger =
        LogManager.getLogger(new Object() {
        }.getClass().getEnclosingClass());
    
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
        TransitionInformation transitionInformation = null;
        
        try {
            transitionInformation = setUpAndExecuteCommand(req);
        } catch (IllegalAccessException | InstantiationException parametersDefinitionException) {
            logger.error("Can't get parameters for command",
                parametersDefinitionException);
        }
        
        doTransit(req, resp, transitionInformation);
    }
    
    /**
     * Create necessary command, sets up it's parameters and runs it.
     *
     * @param req the {@link HttpServletRequest} object that
     *            contains the request the client made of
     *            the servlet
     * @return {@link TransitionInformation}
     * @throws InstantiationException command parameters creation error
     * @throws IllegalAccessException command parameters initialisation error
     */
    private TransitionInformation setUpAndExecuteCommand(HttpServletRequest req)
        throws InstantiationException, IllegalAccessException {
        ActionCommand command =
            new ActionFactory().defineCommand(req.getParameterMap());
        
        CommandParameters commandParams =
            new ParametersFactory(command)
                .defineParameters(req.getParameterMap());
        
        return command.execute(commandParams);
    }
    
    /**
     * Transits request and response according transition information
     *
     * @param req                   the {@link HttpServletRequest} object that
     *                              contains the request the client made of
     *                              the servlet
     * @param resp                  the {@link HttpServletResponse} object that
     *                              contains the response the servlet returns
     *                              to the client
     * @param transitionInformation {@link TransitionInformation}
     * @throws IOException      transit end point not found
     * @throws ServletException servlet exception
     */
    private void doTransit(HttpServletRequest req, HttpServletResponse resp,
                           TransitionInformation transitionInformation)
        throws IOException, ServletException {
        if (transitionInformation != null) {
            
            TransitionMethod method = transitionInformation.getMethod();
            
            this.setDisplayData(req, transitionInformation.getDisplayData());
            
            switch (method) {
                case FORWARD:
                    RequestDispatcher dispatcher = getServletContext()
                                                       .getRequestDispatcher(
                                                           transitionInformation
                                                               .getPage());
                    dispatcher.forward(req, resp);
                    break;
                case REDIRECT:
                    resp.sendRedirect(
                        req.getContextPath() + transitionInformation.getPage());
                    break;
                default:
                    break;
            }
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }
    
    /**
     * Sets request attributes according display data
     *
     * @param req         the {@link HttpServletRequest} object that
     *                    contains the request the client made of
     *                    the servlet
     * @param displayData map of data to display
     */
    private void setDisplayData(HttpServletRequest req,
                                Map<String, Object> displayData) {
        for (Map.Entry<String, Object> entry : displayData.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }
    
}