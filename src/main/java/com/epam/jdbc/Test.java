package com.epam.jdbc;

import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DAOType;

public final class Test {
    
    public static void main(String... args) {
        DAOFactory dao = DAOFactory.getInstance(DAOType.ORACLE);
    }
}
