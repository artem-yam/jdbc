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

public class OracleEmployeeDAO implements EmployeeDAO {
    private static final Logger logger = LogManager
                                             .getLogger(new Object() {
                                             }.getClass().getEnclosingClass());
    
    private static final int ID_COLUMN = 1;
    private static final int LAST_NAME_COLUMN = 2;
    private static final int FIRST_NAME_COLUMN = 3;
    
    private DataSource dataSource;
    private OracleEmployeeDAOWrapper wrapper;
    
    public OracleEmployeeDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        wrapper = new OracleEmployeeDAOWrapper(dataSource);
        
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }
    
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        
        try (ResultSet rs = wrapper.getAllEmployees()) {
            while (rs.next()) {
                employees.add(
                    new Employee(rs.getLong(ID_COLUMN), rs.getString(
                        LAST_NAME_COLUMN), rs.getString(
                        FIRST_NAME_COLUMN)));
            }
        } catch (SQLException e) {
            logger.error("SQL error while receiving employees list", e);
        }
        
        return employees;
    }
    
    @Override
    public Employee createEmployee(String firstName, String lastName) {
        Employee newEmployee = null;
        
        try (ResultSet rs = wrapper.createEmployee(firstName, lastName)) {
            rs.next();
            
            newEmployee =
                new Employee(rs.getLong(ID_COLUMN), lastName,
                    firstName);
            
        } catch (SQLException e) {
            logger.error("SQL error while creating new employee", e);
        }
        
        return newEmployee;
    }
    
}
