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
        
        ActionCommand command =
            new ActionFactory().defineCommand(req.getParameterMap());
        
        TransitionInformation pageHandler = null;
        
        try {
            CommandParameters commandParams =
                new ParametersFactory(command)
                    .defineParameters(req.getParameterMap());
            
            pageHandler = command.execute(commandParams);
        } catch (IllegalAccessException | InstantiationException parametersDefinitionException) {
            logger.error("Can't get parameters for command",
                parametersDefinitionException);
        }
        
        if (pageHandler != null) {
            
            TransitionMethod method = pageHandler.getMethod();
            
            Map<String, Object> parametersToSet =
                pageHandler.getParametersToSet();
            
            for (Map.Entry<String, Object> entry : parametersToSet.entrySet()) {
                req.setAttribute(entry.getKey(), entry.getValue());
            }
            
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