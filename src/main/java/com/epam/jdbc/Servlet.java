package com.epam.jdbc;

import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DAOType;
import com.epam.jdbc.datalayer.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() {
        DAOFactory factory = DAOFactory.getInstance(DAOType.ORACLE);
        EmployeeDAO employeeDAO = factory.getEmployeeDAO();
        
        getServletContext().setAttribute("employeeDAO", employeeDAO);
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        
        EmployeeDAO empDAO = ((EmployeeDAO) getServletContext()
                                                .getAttribute("employeeDAO"));
        empDAO.createEmployee(firstName, lastName);
        
        req.getSession().setAttribute("createdEmployee",
            "Employee " + firstName + " " + lastName + "was created");
        
        resp.sendRedirect(req.getContextPath());
        ;
    }
    
}