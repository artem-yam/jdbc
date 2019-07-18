package com.epam.jdbc.datalayer;

import com.epam.jdbc.datalayer.dto.Employee;

import java.util.List;

/**
 * Employee DAO
 */
public interface EmployeeDAO {

    /**
     * Gets list of all employees
     *
     * @return List of {@link Employee}
     */
    List<Employee> getAllEmployees();

    /**
     * Creates new employee
     *
     * @param firstName new employee first name
     * @param lastName  new employee last name
     * @return created {@link Employee}
     */
    Employee createEmployee(String firstName, String lastName);
}
