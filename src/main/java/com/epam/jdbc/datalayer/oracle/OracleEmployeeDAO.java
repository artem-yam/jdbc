package com.epam.jdbc.datalayer.oracle;

import com.epam.jdbc.datalayer.EmployeeDAO;
import com.epam.jdbc.datalayer.dto.Employee;
import com.epam.jdbc.datalayer.exception.DataReceiveException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
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
     * Message for error while connecting DB
     */
    private static final String CONNECTION_ERROR_MESSAGE =
        "DB connection error";
    /**
     * Message for error while interacting with DB
     */
    private static final String DB_INTERACTION_ERROR_MESSAGE =
        "DB interaction error";
    
    /**
     * Data source to connect DB
     */
    private DataSource dataSource;
    
    /**
     * Class constructor
     *
     * @param dataSource {@link DataSource}
     */
    public OracleEmployeeDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public List<Employee> getAllEmployees() throws DataReceiveException {
        List<Employee> employees = new ArrayList<>();
        
        try {
            Connection con = dataSource.getConnection();
            
            try (ResultSet rs = new OracleResultSetsGetter(
                con).getAllEmployees()) {
                while (rs.next()) {
                    employees.add(
                        new Employee(rs.getLong(ID_COLUMN), rs.getString(
                            LAST_NAME_COLUMN), rs.getString(
                            FIRST_NAME_COLUMN)));
                }
            } catch (SQLException employeesSQLException) {
                throw new DataReceiveException(DB_INTERACTION_ERROR_MESSAGE,
                    employeesSQLException);
            }
        } catch (SQLException connectionError) {
            throw new DataReceiveException(CONNECTION_ERROR_MESSAGE,
                connectionError);
        }
        
        logger.debug("Received employees list {}", employees);
        
        return employees;
    }
    
    @Override
    public Employee createEmployee(String firstName, String lastName)
        throws DataReceiveException {
        
        logger.debug("Starting employee {} {} creation", firstName, lastName);
        
        Employee newEmployee;
        
        try {
            Connection con = dataSource.getConnection();
            
            try (ResultSet rs = new OracleResultSetsGetter(
                con).createEmployee(firstName, lastName)) {
                rs.next();
                
                newEmployee =
                    new Employee(rs.getLong(ID_COLUMN), lastName,
                        firstName);
                
            } catch (SQLException employeesSQLException) {
                throw new DataReceiveException(DB_INTERACTION_ERROR_MESSAGE,
                    employeesSQLException);
            }
        } catch (SQLException connectionError) {
            throw new DataReceiveException(CONNECTION_ERROR_MESSAGE,
                connectionError);
        }
        
        logger.debug("Employee {} was created", newEmployee);
        
        return newEmployee;
    }
    
}
