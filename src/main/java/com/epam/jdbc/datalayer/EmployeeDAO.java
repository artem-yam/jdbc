package com.epam.jdbc.datalayer;

import com.epam.jdbc.datalayer.dto.Employee;

import java.util.List;

public interface EmployeeDAO {
    
    List<Employee> getAllEmployees();
    
    Employee createEmployee(String firstName, String lastName);
}
