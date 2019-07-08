package com.epam.jdbc.command;

import com.epam.jdbc.GoToPageHandler;
import com.epam.jdbc.GoToPageMethodEnum;
import com.epam.jdbc.datalayer.EmployeeDAO;

import javax.servlet.http.HttpServletRequest;

public class AdminPageCommand implements ActionCommand {
    @Override
    public GoToPageHandler execute(HttpServletRequest request) {
        request.getSession().removeAttribute("userAction");
        
        EmployeeDAO userDAO =
            (EmployeeDAO) request.getServletContext().getAttribute("userDAO");
        
        request.getSession().setAttribute("usersList", userDAO.getAllEmployees());
        
        String page = ("path.page.admin");
        
        GoToPageHandler handler =
            new GoToPageHandler(GoToPageMethodEnum.FORWARD, page);
        return handler;
    }
}