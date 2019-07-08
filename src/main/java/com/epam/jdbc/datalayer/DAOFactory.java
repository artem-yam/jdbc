package com.epam.jdbc.datalayer;

public interface DAOFactory {
    static DAOFactory getInstance(DAOType type) {
        return type.getDAOFactory();
    }
    
    EmployeeDAO getEmployeeDAO();
}
