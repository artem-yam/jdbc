package com.epam.jdbc.command;

import com.epam.jdbc.GoToPageHandler;
import com.epam.jdbc.GoToPageMethodEnum;
import com.epam.jdbc.datalayer.DAOFactory;
import com.epam.jdbc.datalayer.DAOType;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    private static final String INDEX_PAGE = "/jsp/main.jsp";
    
    @Override
    public GoToPageHandler execute(HttpServletRequest request) {
        DAOFactory factory = DAOFactory.getInstance(DAOType.ORACLE);
        
        request.setAttribute("employees",
            factory.getEmployeeDAO().getAllEmployees());
        return new GoToPageHandler(GoToPageMethodEnum.FORWARD, INDEX_PAGE);
    }
}