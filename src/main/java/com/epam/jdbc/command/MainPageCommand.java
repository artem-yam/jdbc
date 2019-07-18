package com.epam.jdbc.command;

import com.epam.jdbc.GoToPageHandler;
import com.epam.jdbc.GoToPageMethodEnum;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DAOType;
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
    private static final String INDEX_PAGE = "/jsp/main.jsp";
    
    @Override
    public GoToPageHandler execute(HttpServletRequest request) {
        DAOFactory factory = DAOFactory.getInstance(DAOType.ORACLE);
        
        request.setAttribute("employees",
            factory.getEmployeeDAO().getAllEmployees());
        
        logger.info("Redirecting to main page");
        
        return new GoToPageHandler(GoToPageMethodEnum.FORWARD, INDEX_PAGE);
    }
}