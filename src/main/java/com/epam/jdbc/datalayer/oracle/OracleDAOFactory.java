package com.epam.jdbc.datalayer.oracle;

import com.epam.jdbc.MyDataSource;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.EmployeeDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
            try {
                instance.getDataSource();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private void getDataSource() throws NamingException, RemoteException {

        //Registry registry = LocateRegistry.createRegistry(1234);

        Properties props = new Properties();
        /*props.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        props.put(Context.PROVIDER_URL, "rmi://localhost:1234");*/

        props.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, "ldap://localhost:8080");

        Context ctx = new InitialContext(props);
        ctx.bind(DATA_SOURCE_NAME, new MyDataSource("dataSource"));
        System.out.println("Data source binded");

        dataSource = (DataSource) ctx.lookup(DATA_SOURCE_NAME);
        //dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/myoracle");

        System.out.println("Data source received");
        ctx.close();

    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new OracleEmployeeDAO(dataSource);
    }
}
