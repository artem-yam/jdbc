package com.epam.jdbc.command;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        
        String action = request.getParameter("command");
        
        System.out.println("command = " + action);
        
        if (action != null && !action.isEmpty()) {
            try {
                CommandEnum currentEnum =
                    CommandEnum.valueOf(action.toUpperCase());
                current = currentEnum.getCurrentCommand();
            } catch (IllegalArgumentException e) {
            
            }
        }
        
        return current;
    }
}