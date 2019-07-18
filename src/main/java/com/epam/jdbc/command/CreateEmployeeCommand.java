package com.epam.jdbc.command;

import com.epam.jdbc.GoToPageHandler;
import com.epam.jdbc.GoToPageMethodEnum;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DAOType;
import com.epam.jdbc.datalayer.EmployeeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to create new employee
 */
public class CreateEmployeeCommand implements ActionCommand {
    /**
     * Logger
     */
    private static final Logger logger = LogManager
                                             .getLogger(new Object() {
                                             }.getClass().getEnclosingClass());
    
    /**
     * First name parameter
     */
    private static final String FIRST_NAME_PARAMETER = "firstName";
    /**
     * Last name parameter
     */
    private static final String LAST_NAME_PARAMETER = "lastName";
    
    @Override
    public GoToPageHandler execute(HttpServletRequest request) {
        DAOFactory factory = DAOFactory.getInstance(DAOType.ORACLE);
        EmployeeDAO employeeDAO = factory.getEmployeeDAO();
        
        String firstName = request.getParameter(FIRST_NAME_PARAMETER);
        String lastName = request.getParameter(LAST_NAME_PARAMETER);
        
        employeeDAO.createEmployee(firstName, lastName);
        
        logger.info("New employee {} {} created", firstName,
            lastName);
        
        return new GoToPageHandler(GoToPageMethodEnum.REDIRECT, "");
    }
}