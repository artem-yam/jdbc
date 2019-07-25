package com.epam.jdbc.command;

import com.epam.jdbc.command.dto.TransitionInformation;
import com.epam.jdbc.command.dto.TransitionMethod;
import com.epam.jdbc.command.parameters.CommandParameters;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DataSourceType;
import com.epam.jdbc.datalayer.EmployeeDAO;
import com.epam.jdbc.datalayer.exception.DataReceiveException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

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
     * Name of attribute containing error
     */
    private static final String ERROR_ATTRIBUTE = "DBError";
    
    /**
     * Value of attribute containing error
     */
    private static final String ERROR_MESSAGE =
        "Can't create new employees: %s";
    
    @Override
    public TransitionInformation execute(CommandParameters parameters) {
        
        DAOFactory factory =
            DAOFactory.getInstance(DataSourceType.ORACLE);
        EmployeeDAO employeeDAO = factory.getEmployeeDAO();
        
        String firstName;
        String lastName;
        Map<String, Object> displayData = new HashMap<>();
        
        try {
            firstName = parameters.getFirstName();
            lastName = parameters.getLastName();
            
            employeeDAO.createEmployee(firstName, lastName);
            
            logger.info("New employee {} {} created", firstName,
                lastName);
        } catch (DataReceiveException dataReceiveException) {
            logger.error("DB error", dataReceiveException);
            displayData.put(ERROR_ATTRIBUTE, String.format(ERROR_MESSAGE,
                dataReceiveException.getMessage()));
        }
        
        return new TransitionInformation(TransitionMethod.REDIRECT, "",
            displayData);
    }
}