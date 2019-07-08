package com.epam.jdbc.datalayer.oracle;

import com.epam.jdbc.datalayer.EmployeeDAO;
import com.epam.jdbc.datalayer.dto.Employee;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OracleEmployeeDAO implements EmployeeDAO {
    private DataSource dataSource;
    
    public OracleEmployeeDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> lst = new ArrayList<Employee>();
        
        try {
            Statement st = null;
            try {
                st = dataSource.getConnection().createStatement();
                ResultSet rs = null;
                try {
                    rs = st.executeQuery(
                        ("request.users.select.all.orderbystatus"));
                    
                    //lst = formUserList(rs);
                    
                    if (lst.size() > 0) {
                        System.out.println(lst);
                    } else {
                        System.out.println("Not found");
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    } else {
                        System.err.println("ошибка во время чтения из БД");
                    }
                }
            } finally {
                if (st != null) {
                    st.close();
                } else {
                    System.err.println("Statement не создан");
                }
            }
        } catch (SQLException e) {
            System.err.println("DB connection error: " + e);
        }
        
        return lst;
    }
    
    @Override public void createEmployee(String firstName, String lastName) {
        PreparedStatement ps = null;
        try {
           /* int id = getNextId();
            
            ps = dataSource.getConnection()
                     .prepareStatement(("request.users.insert"));
            ps.setInt(1, id);
            ps.setString(2, userLogin);
            ps.setString(3, userPass);
            ps.setString(4, userData.getFullName().getLastName());
            ps.setString(5, userData.getFullName().getFirstName());
            ps.setString(6, userData.getFullName().getMiddleName());
            ps.setString(7, userType);
            ps.setString(8, userData.getPhoneNumber());
            ps.setString(9, userData.getExtraInfo());
            ps.setString(10, "offline");
            ps.setString(11, userData.getDepartmentName());
            ps.setString(12, userData.getDegreeAndPost());
            */
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB connection error: " + e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
