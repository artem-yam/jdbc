package com.epam.jdbc.command;

public enum CommandEnum {
    CREATEEMPLOYEE {
        {
            this.command = new CreateEmployeeCommand();
        }
    };
    
    ActionCommand command;
    
    public ActionCommand getCurrentCommand() {
        return command;
    }
}