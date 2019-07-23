package com.epam.jdbc.command;

import com.epam.jdbc.command.dto.TransitionInformation;
import com.epam.jdbc.command.dto.TransitionMethod;
import com.epam.jdbc.command.parameters.CommandParameters;
import com.epam.jdbc.command.parameters.CreateEmployeeParameters;
import com.epam.jdbc.command.parameters.HasParameters;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.EmployeeDAO;
import com.epam.jdbc.datalayer.exception.DataReceiveException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command to create new employee
 */
@HasParameters(parameters = CreateEmployeeParameters.class)
public class CreateEmployeeCommand implements ActionCommand {
    /**
     * Logger
     */
    private static final Logger logger = LogManager
            .getLogger(new Object() {
            }.getClass().getEnclosingClass());

    private static final String ERROR_ATTRIBUTE = "DBError";
    private static final String ERROR_MESSAGE =
            "Can't create new employees: %s";

    @Override
    public TransitionInformation execute(CommandParameters parameters) {
        parameters.getSession().removeAttribute(ERROR_ATTRIBUTE);

        DAOFactory factory =
                DAOFactory.getInstance(parameters.getDataSourceType());
        EmployeeDAO employeeDAO = factory.getEmployeeDAO();

        String firstName =
                ((CreateEmployeeParameters) parameters).getFirstName();
        String lastName =
                ((CreateEmployeeParameters) parameters).getLastName();

        try {
            employeeDAO.createEmployee(firstName, lastName);
        } catch (DataReceiveException dataReceiveException) {
            parameters.getSession().setAttribute(ERROR_ATTRIBUTE,
                    String.format(ERROR_MESSAGE,
                            dataReceiveException.getMessage()));
        }

        logger.info("New employee {} {} created", firstName,
                lastName);

        return new TransitionInformation(TransitionMethod.REDIRECT, "");
    }
}