package com.epam.jdbc.datalayer;

import com.epam.jdbc.datalayer.oracle.OracleDAOFactory;

import javax.naming.NamingException;

public enum DAOType {
    ORACLE {
        public DAOFactory getDAOFactory() {
            DAOFactory oracleDAOFactory = null;
            try {
                oracleDAOFactory = OracleDAOFactory.getInstance();
            } catch (NamingException e) {
                e.printStackTrace();
            }
            return oracleDAOFactory;
        }
    };
    
    public abstract DAOFactory getDAOFactory();
}
