package com.epam.jdbc.datalayer.oracle;

import com.epam.jdbc.datalayer.EmployeeDAO;
import com.epam.jdbc.datalayer.dto.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleEmployeeDAO implements EmployeeDAO {
    private static final Logger logger = LogManager
                                             .getLogger(new Object() {
                                             }.getClass().getEnclosingClass());
    
    private static final String SELECT_ALL = "SELECT * FROM EMPLOYEES";
    private static final String CREATE_EMPLOYEE =
        "INSERT INTO EMPLOYEES(EMPLOYEEID,LASTNAME,FIRSTNAME) VALUES (?,?,?)";
    private static final String SELECT_EMPLOYEE_NEXT_ID =
        "SELECT EMPLOYEES_SEQ.NEXTVAL FROM dual";
    private static final int ID_COLUMN_NUMBER = 1;
    private static final int FIRST_NAME_COLUMN_NUMBER = 3;
    private static final int LAST_NAME_COLUMN_NUMBER = 2;
    
    private DataSource dataSource;
    
    public OracleEmployeeDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }
    
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        
        try (Connection con = dataSource.getConnection()) {
            try (Statement st = con.createStatement()) {
                try (ResultSet rs = st.executeQuery(SELECT_ALL)) {
                    
                    while (rs.next()) {
                        employees.add(
                            new Employee(rs.getString(
                                FIRST_NAME_COLUMN_NUMBER), rs.getString(
                                LAST_NAME_COLUMN_NUMBER)));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQL error while receiving employees list " + e);
        }
        
        return employees;
    }
    
    @Override
    public void createEmployee(String firstName, String lastName) {
        
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(
                CREATE_EMPLOYEE)) {
                try (Statement st = con.createStatement()) {
                    try (ResultSet rs = st.executeQuery(
                        SELECT_EMPLOYEE_NEXT_ID)) {
                        rs.next();
                        ps.setObject(ID_COLUMN_NUMBER,
                            rs.getLong(ID_COLUMN_NUMBER));
                    }
                }
                ps.setString(LAST_NAME_COLUMN_NUMBER, lastName);
                ps.setString(FIRST_NAME_COLUMN_NUMBER, firstName);
                
                ps.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL error while creating new employee " + e);
        }
    }
    
}
