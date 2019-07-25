package com.epam.jdbc.command;

import com.epam.jdbc.command.dto.TransitionInformation;
import com.epam.jdbc.command.dto.TransitionMethod;
import com.epam.jdbc.command.parameters.CommandParameters;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DataSourceType;
import com.epam.jdbc.datalayer.exception.DataReceiveException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Command to redirect to main page
 */
public class MainPageCommand implements ActionCommand {
    /**
     * Logger
     */
    private static final Logger logger = LogManager
                                             .getLogger(new Object() {
                                             }.getClass().getEnclosingClass());
    
    /**
     * Name of attribute containing employees list
     */
    private static final String EMPLOYEES_ATTRIBUTE = "employees";
    
    /**
     * Main page
     */
    private static final String INDEX_PAGE = "/WEB-INF/jsp/main.jsp";
    
    /**
     * Name of attribute containing error
     */
    private static final String ERROR_ATTRIBUTE = "DBError";
    /**
     * Value of attribute containing error
     */
    private static final String ERROR_MESSAGE = "Can't get all employees: %s";
    
    @Override
    public TransitionInformation execute(CommandParameters parameters) {
        
        DAOFactory factory =
            DAOFactory.getInstance(DataSourceType.ORACLE);
        
        Map<String, Object> parametersToSet = new HashMap<>();
        
        try {
            parametersToSet.put(EMPLOYEES_ATTRIBUTE,
                factory.getEmployeeDAO().getAllEmployees());
        } catch (DataReceiveException dataReceiveException) {
            logger.error("DB error", dataReceiveException);
            parametersToSet.put(ERROR_ATTRIBUTE, String.format(ERROR_MESSAGE,
                dataReceiveException.getMessage()));
        }
        
        logger.info("Redirecting to main page");
        
        return new TransitionInformation(TransitionMethod.FORWARD, INDEX_PAGE,
            parametersToSet);
    }
}