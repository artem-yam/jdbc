package com.epam.jdbc.datalayer.oracle;

import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.EmployeeDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Locale;

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
            try {
                instance.getDataSource();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    
    private void getDataSource()
        throws NamingException, RemoteException, AlreadyBoundException,
                   NotBoundException, SQLException {
        
/*        LocateRegistry.createRegistry(8088);
        
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,
            "com.sun.jndi.rmi.registry.RegistryContextFactory");
        props.put(Context.PROVIDER_URL, "rmi://localhost:8088");
        */
        Locale.setDefault(Locale.ENGLISH);
        Context ctx = new InitialContext(/*props*/);
        
       /* OracleDataSource ods = new OracleDataSource();
        
        ods.setUser("system");
        ods.setPassword("SYSTEM");
        ods.setDatabaseName("Test Name");
        ods.setDriverType("thin");
        ods.setServerName("localhost");
        ods.setPortNumber(1521);
        ods.setServiceName("xe");
        */
        //ctx.bind(DATA_SOURCE_NAME, ods);
        
        // System.out.println("Data source binded");
        
        dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
        //dataSource = (DataSource) ctx.lookup(DATA_SOURCE_NAME);
        System.out.println("Data source received");
       // dataSource.getConnection();
        
        ctx.close();
    }
    
    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new OracleEmployeeDAO(dataSource);
    }
}
