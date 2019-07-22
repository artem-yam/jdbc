package com.epam.jdbc.datalayer;

import com.epam.jdbc.datalayer.oracle.OracleDAOFactory;

/**
 * Enum of supported DAO types
 */
public enum DataSourceType {
    /**
     * Oracle DB
     */
    ORACLE {
        public DAOFactory getDAOFactory() {
            return OracleDAOFactory.getInstance();
        }
    };
    
    /**
     * Gets instance of certain DAO factory
     *
     * @return {@link DAOFactory}
     */
    public abstract DAOFactory getDAOFactory();
}
