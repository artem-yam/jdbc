package com.epam.jdbc;

import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DAOType;
import com.epam.jdbc.datalayer.EmployeeDAO;
import com.epam.jdbc.datalayer.dto.Employee;

import java.util.List;

public final class Test {
    
    public static void main(String... args) {
        
        DAOFactory dao = DAOFactory.getInstance(DAOType.ORACLE);
        EmployeeDAO empDAO = dao.getEmployeeDAO();
        
        //empDAO.createEmployee("John", "Doe");
    
        List<Employee> employees = empDAO.getAllEmployees();
    
        int i = 1;
        for (Employee emp : employees) {
            System.out.println(emp + " №" + i++);
        }
        
    }
}
