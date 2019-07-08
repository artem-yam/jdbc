package com.epam.jdbc.datalayer.oracle;

import com.epam.jdbc.MyDataSource;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.EmployeeDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

public class OracleDAOFactory implements DAOFactory {
    private static final String DATA_SOURCE_NAME = "jdbc/oracleDS";
    private static OracleDAOFactory instance;
    private DataSource dataSource;
    
    private OracleDAOFactory() {
    }
    
    public static OracleDAOFactory getInstance()
        throws NamingException {
        if (instance == null) {
            instance = new OracleDAOFactory();
            instance.getDataSource();
        }
        return instance;
    }
    
    private void getDataSource() throws NamingException {
        Properties props = new Properties ();
      //  props.put (Context.INITIAL_CONTEXT_FACTORY, "com.sun.xml.internal.bind.v2.ContextFactory");
        Context ctx = new InitialContext(props);
        
        ctx.bind(DATA_SOURCE_NAME, new MyDataSource());
        
        dataSource = (DataSource)
                         ctx.lookup(DATA_SOURCE_NAME);
    }
    
    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new OracleEmployeeDAO(dataSource);
    }
}
