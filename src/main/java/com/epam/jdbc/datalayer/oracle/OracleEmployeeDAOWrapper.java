package com.epam.jdbc.datalayer.oracle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleEmployeeDAOWrapper implements AutoCloseable {
    
    private static final String SELECT_ALL = "SELECT * FROM EMPLOYEES";
    private static final String CREATE_EMPLOYEE_QUERY =
        "INSERT INTO EMPLOYEES(EMPLOYEEID,LASTNAME,FIRSTNAME) VALUES (EMPLOYEES_SEQ.NEXTVAL,?,?)";
    private static final int LAST_NAME_PARAMETER_NUMBER = 1;
    private static final int FIRST_NAME_PARAMETER_NUMBER = 2;
    
    private DataSource dataSource;
    
    public OracleEmployeeDAOWrapper(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public ResultSet getAllEmployees() throws SQLException {
        return dataSource.getConnection().createStatement()
                   .executeQuery(SELECT_ALL);
    }
    
    public ResultSet createEmployee(String firstName, String lastName)
        throws SQLException {
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(
            CREATE_EMPLOYEE_QUERY, new int[]{1});
        ps.setString(LAST_NAME_PARAMETER_NUMBER, lastName);
        ps.setString(FIRST_NAME_PARAMETER_NUMBER, firstName);
        
        ps.execute();
        
        return ps.getGeneratedKeys();
    }
    
    @Override
    public void close() throws Exception {
        
    }
}
