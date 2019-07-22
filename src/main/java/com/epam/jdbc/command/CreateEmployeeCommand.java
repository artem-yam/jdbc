package com.epam.jdbc.command;

import com.epam.jdbc.command.dto.TransitionInformation;
import com.epam.jdbc.command.dto.TransitionMethod;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DataSourceType;
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
    
    @Override
    public TransitionInformation execute(HttpServletRequest req) {
        DAOFactory factory = DAOFactory.getInstance(DataSourceType.ORACLE);
        EmployeeDAO employeeDAO = factory.getEmployeeDAO();
        
        /*String firstName =
            ((CreateEmployeeParameters) parameters).getFirstName();
        String lastName = ((CreateEmployeeParameters) parameters).getLastName();*/
        
        String firstName =
            req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        
        employeeDAO.createEmployee(firstName, lastName);
        
        logger.info("New employee {} {} created", firstName,
            lastName);
        
        return new TransitionInformation(TransitionMethod.REDIRECT, "");
    }
}