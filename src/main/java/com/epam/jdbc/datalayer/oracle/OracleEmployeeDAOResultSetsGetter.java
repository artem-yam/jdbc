package com.epam.jdbc.datalayer.oracle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Accesses DB and returns queries result sets
 */
public class OracleEmployeeDAOResultSetsGetter implements AutoCloseable {
    /**
     * Logger
     */
    private static final Logger logger = LogManager
            .getLogger(new Object() {
            }.getClass().getEnclosingClass());

    /**
     * Select all employees query
     */
    private static final String SELECT_ALL_EMPLOYEES =
            "SELECT * FROM EMPLOYEES";
    /**
     * Create new employee query
     */
    private static final String CREATE_EMPLOYEE_QUERY =
            "INSERT INTO EMPLOYEES(EMPLOYEEID,LASTNAME,FIRSTNAME) VALUES (EMPLOYEES_SEQ.NEXTVAL,?,?)";
    /**
     * Number of parameter for last name
     */
    private static final int LAST_NAME_PARAMETER_NUMBER = 1;
    /**
     * Number of parameter for first name
     */
    private static final int FIRST_NAME_PARAMETER_NUMBER = 2;

    /**
     * Data source to connect DB
     */
    private DataSource dataSource;

    /**
     * Constructor
     *
     * @param dataSource {@link DataSource}
     */
    public OracleEmployeeDAOResultSetsGetter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Executes query to get all employees
     *
     * @return Query {@link ResultSet}
     * @throws SQLException DB error
     */
    @DBQuery(text = SELECT_ALL_EMPLOYEES)
    public ResultSet getAllEmployees() throws SQLException {

        String queryText = "";
        try {
            queryText = getClass().getDeclaredMethod("getAllEmployees")
                    .getAnnotation(DBQuery.class).text();
        } catch (NoSuchMethodException noSuchMethodException) {
            logger.error("Method wasn't found", noSuchMethodException);
        }

        logger.debug("Executing query {}", queryText);

        ResultSet rs = dataSource.getConnection().createStatement()
                .executeQuery(queryText);

        logger.debug("Returning result set {}", rs);

        return rs;
    }

    /**
     * Executes query to create new employee
     *
     * @param firstName new employee first name
     * @param lastName  new employee last name
     * @return Query {@link ResultSet}
     * @throws SQLException DB error
     */
    @DBQuery(text = CREATE_EMPLOYEE_QUERY)
    public ResultSet createEmployee(String firstName, String lastName)
            throws SQLException {

        String queryText = "";
        try {
            queryText = getClass()
                    .getDeclaredMethod("createEmployee", String.class,
                            String.class)
                    .getAnnotation(DBQuery.class).text();
        } catch (NoSuchMethodException noSuchMethodException) {
            logger.error("Method wasn't found", noSuchMethodException);
        }

        logger.debug("Executing query {} with parameters {}, {}", queryText,
                firstName, lastName);

        PreparedStatement ps = dataSource.getConnection().prepareStatement(
                queryText, new int[]{1});
        ps.setString(LAST_NAME_PARAMETER_NUMBER, lastName);
        ps.setString(FIRST_NAME_PARAMETER_NUMBER, firstName);

        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();

        logger.debug("Returning result set {}", rs);

        return rs;
    }

    @Override
    public void close() throws Exception {

    }
}
