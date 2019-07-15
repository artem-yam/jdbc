package com.epam.jdbc.datalayer;

import com.epam.jdbc.datalayer.oracle.OracleDAOFactory;

public enum DAOType {
    ORACLE {
        public DAOFactory getDAOFactory() {
            return OracleDAOFactory.getInstance();
        }
    };

    public abstract DAOFactory getDAOFactory();
}
