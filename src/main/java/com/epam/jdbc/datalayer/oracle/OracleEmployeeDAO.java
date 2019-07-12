package com.epam.jdbc.datalayer.oracle;

import com.epam.jdbc.datalayer.EmployeeDAO;
import com.epam.jdbc.datalayer.dto.Employee;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleEmployeeDAO implements EmployeeDAO {
    private static final String SELECT_ALL = "SELECT * FROM EMPLOYEES";
    private static final String CREATE_EMPLOYEE =
        "INSERT INTO EMPLOYEES(EMPLOYEEID,LASTNAME,FIRSTNAME) VALUES (?,?,?)";
    private static final String SELECT_EMPLOYEE_NEXT_ID =
        "SELECT EMPLOYEES_SEQ.NEXTVAL FROM dual";
    
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
    
        Connection con=null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        /*try (Connection con = ((OracleDataSource)dataSource).getConnection()) {*/
            try (Statement st = con.createStatement()) {
                try (ResultSet rs = st.executeQuery(SELECT_ALL)) {
                    
                    while (rs.next()) {
                        employees.add(
                            new Employee(rs.getString(3), rs.getString(2)));
                    }
                }
            }
       /* }*/ catch (SQLException e) {
            e.printStackTrace();
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
                        System.out.println("New id = " + rs.getLong(1));
                        ps.setLong(1, rs.getLong(1));
                    }
                }
                ps.setString(2, lastName);
                ps.setString(3, firstName);
                
                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
