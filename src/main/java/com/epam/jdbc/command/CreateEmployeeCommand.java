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

    @Override
    public GoToPageHandler execute(HttpServletRequest request) {
        DAOFactory factory = DAOFactory.getInstance(DAOType.ORACLE);
        EmployeeDAO employeeDAO = factory.getEmployeeDAO();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        employeeDAO.createEmployee(firstName, lastName);

        logger.info("New employee {} {} was created in session {}", firstName,
                lastName, request.getSession().getId());

        return new GoToPageHandler(GoToPageMethodEnum.REDIRECT, "");
    }
}