package com.epam.jdbc.datalayer;

/**
 * Factory of DAO
 */
public interface DAOFactory {

    /**
     * Gets instance of certain DAOFactory
     *
     * @param type {@link DataSourceType}
     * @return {@link DAOFactory}
     */
    static DAOFactory getInstance(DataSourceType type) {
        return type.getDAOFactory();
    }

    /**
     * Employee DAO getter
     *
     * @return {@link EmployeeDAO}
     */
    EmployeeDAO getEmployeeDAO();
}
