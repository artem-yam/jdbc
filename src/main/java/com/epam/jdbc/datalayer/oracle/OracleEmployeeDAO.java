package com.epam.jdbc.datalayer.oracle;

import com.epam.jdbc.datalayer.EmployeeDAO;
import com.epam.jdbc.datalayer.dto.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Employee DAO for oracle DB
 */
public class OracleEmployeeDAO implements EmployeeDAO {
    /**
     * Logger
     */
    private static final Logger logger = LogManager
            .getLogger(new Object() {
            }.getClass().getEnclosingClass());

    /**
     * Number of ID column
     */
    private static final int ID_COLUMN = 1;
    /**
     * Number of last name column
     */
    private static final int LAST_NAME_COLUMN = 2;
    /**
     * Number of first name column
     */
    private static final int FIRST_NAME_COLUMN = 3;

    /**
     * Returns result sets
     */
    private OracleEmployeeDAOResultSetsGetter rsGetter;

    /**
     * Class constructor
     *
     * @param dataSource {@link DataSource}
     */
    public OracleEmployeeDAO(DataSource dataSource) {
        rsGetter = new OracleEmployeeDAOResultSetsGetter(dataSource);

    }


    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (ResultSet rs = rsGetter.getAllEmployees()) {
            while (rs.next()) {
                employees.add(
                        new Employee(rs.getLong(ID_COLUMN), rs.getString(
                                LAST_NAME_COLUMN), rs.getString(
                                FIRST_NAME_COLUMN)));
            }
        } catch (SQLException employeesSQLException) {
            logger.error("SQL error while receiving employees list",
                    employeesSQLException);
        }

        logger.debug("Received employees list {}", employees);

        return employees;
    }


    @Override
    public Employee createEmployee(String firstName, String lastName) {

        logger.debug("Starting employee {} {} creation", firstName, lastName);

        Employee newEmployee = null;


        try (ResultSet rs = rsGetter.createEmployee(firstName, lastName)) {
            rs.next();

            newEmployee =
                    new Employee(rs.getLong(ID_COLUMN), lastName,
                            firstName);

        } catch (SQLException employeesSQLException) {
            logger.error("SQL error while creating new employee",
                    employeesSQLException);
        }

        logger.debug("Employee {} was created", newEmployee);

        return newEmployee;
    }

}
