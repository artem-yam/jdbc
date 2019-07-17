package com.epam.jdbc;

import com.epam.jdbc.command.ActionCommand;
import com.epam.jdbc.command.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() {
        /*DAOFactory factory = DAOFactory.getInstance(DAOType.ORACLE);
        EmployeeDAO employeeDAO = factory.getEmployeeDAO();
        
        getServletContext().setAttribute("employeeDAO", employeeDAO);*/
        
        
       /* try {
            service(null, null);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
  /*
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        
        EmployeeDAO empDAO = ((EmployeeDAO) getServletContext()
                                                .getAttribute("employeeDAO"));
        empDAO.createEmployee(firstName, lastName);
  
        resp.sendRedirect(req.getContextPath());
        */
        
        processRequest(req, resp);
    }
    
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
        throws ServletException, IOException {
        
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        
        GoToPageHandler pageHandler = command.execute(request);
        
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
                    response.sendRedirect(
                        request.getContextPath() + pageHandler.getPage());
                    break;
                default:
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
    
}