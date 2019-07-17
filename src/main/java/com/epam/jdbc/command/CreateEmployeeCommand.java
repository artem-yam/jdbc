package com.epam.jdbc.command;

import com.epam.jdbc.GoToPageHandler;
import com.epam.jdbc.GoToPageMethodEnum;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DAOType;
import com.epam.jdbc.datalayer.EmployeeDAO;

import javax.servlet.http.HttpServletRequest;

public class CreateEmployeeCommand implements ActionCommand {
    
    @Override
    public GoToPageHandler execute(HttpServletRequest request) {
        DAOFactory factory = DAOFactory.getInstance(DAOType.ORACLE);
        EmployeeDAO employeeDAO = factory.getEmployeeDAO();
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        
        employeeDAO.createEmployee(firstName, lastName);
        
/*        request.getServletContext().setAttribute("employees",
            factory.getEmployeeDAO().getAllEmployees());*/
        
        return new GoToPageHandler(GoToPageMethodEnum.REDIRECT, "");
    }
}