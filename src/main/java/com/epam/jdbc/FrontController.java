package com.epam.jdbc;

import com.epam.jdbc.command.ActionCommand;
import com.epam.jdbc.command.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    
    //private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
        throws ServletException, IOException {
        
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        
        // request.getSession().setMaxInactiveInterval(10);
        request.getSession().removeAttribute("messages");
        
        GoToPageHandler pageHandler = command.execute(request);
        
       /* if (getServletContext().getAttribute("DAOFactory") == null) {
            DAOFactory factory = DAOFactory.getInstance(DAOType.ORACLE);
            EmployeeDAO employeeDAO = factory.getEmployeeDAO();
            
            getServletContext().setAttribute("DAOFactory", factory);
            getServletContext().setAttribute("employeeDAO", employeeDAO);
        }*/
        
        if (pageHandler != null) {
            
            GoToPageMethodEnum method = pageHandler.getMethod();
            
            switch (method) {
                case FORWARD:
                    RequestDispatcher dispatcher = getServletContext()
                                                       .getRequestDispatcher(
                                                           pageHandler
                                                               .getPage());
                    dispatcher.forward(request, response);
                    break;
                case REDIRECT:
                    System.out
                        .println("IndexPath = " + request.getContextPath());
                    //+ ("path.page.index"));
                    response.sendRedirect(
                        request.getContextPath() + pageHandler.getPage());
                    break;
                default:
                    break;
            }
        } else {
            
            String page = ""; //("path.page.index");
            //request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}