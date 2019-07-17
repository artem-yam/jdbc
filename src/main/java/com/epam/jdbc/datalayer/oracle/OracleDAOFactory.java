package com.epam.jdbc.datalayer.oracle;

import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.EmployeeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Locale;

public class OracleDAOFactory implements DAOFactory {
    private static final Logger logger = LogManager
                                             .getLogger(new Object() {
                                             }.getClass().getEnclosingClass());
    
    private static final String DATA_SOURCE_NAME = "java:/comp/env/jdbc/MyDB";
    private static volatile OracleDAOFactory instance;
    private DataSource dataSource;
    
    private OracleDAOFactory() {
    }
    
    public static OracleDAOFactory getInstance() {
        OracleDAOFactory factory = instance;
        if (factory == null) {
            synchronized (OracleDAOFactory.class) {
                factory = instance;
                if (factory == null) {
                    instance = factory = new OracleDAOFactory();
                    factory.getDataSource();
                }
            }
        }
        return factory;
    }
    
    private void getDataSource() {
        Locale.setDefault(Locale.ENGLISH);
        
        Context ctx;
        try {
            ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup(DATA_SOURCE_NAME);
            ctx.close();
        } catch (NamingException e) {
            logger.error("Error while receiving data source object" + e);
        }
    }
    
    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new OracleEmployeeDAO(dataSource);
    }
}
