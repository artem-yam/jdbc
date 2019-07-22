package com.epam.jdbc.command;

import com.epam.jdbc.command.dto.TransitionInformation;
import com.epam.jdbc.command.dto.TransitionMethod;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DataSourceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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
     * Main page
     */
    private static final String INDEX_PAGE = "/WEB-INF/jsp/main.jsp";
    
    @Override
    public TransitionInformation execute(HttpServletRequest req) {
        DAOFactory factory = DAOFactory.getInstance(DataSourceType.ORACLE);
        
        req.setAttribute("employees",
            factory.getEmployeeDAO().getAllEmployees());
        
        logger.info("Redirecting to main page");
        
        return new TransitionInformation(TransitionMethod.FORWARD, INDEX_PAGE);
    }
}