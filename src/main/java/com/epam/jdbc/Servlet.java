package com.epam.jdbc;

import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DAOType;
import com.epam.jdbc.datalayer.EmployeeDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("Servlet " + this.getServletName() + " has started");

        DAOFactory factory = DAOFactory.getInstance(DAOType.ORACLE);
        EmployeeDAO employeeDAO = factory.getEmployeeDAO();

        getServletContext().setAttribute("employeeDAO", employeeDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        EmployeeDAO empDAO = ((EmployeeDAO) getServletContext()
                .getAttribute("employeeDAO"));
        empDAO.createEmployee(firstName, lastName);

        request.getSession().setAttribute("createdEmployee",
                "Employee " + firstName + " " + lastName + "was created");

        response.sendRedirect(request.getContextPath());
    }
}