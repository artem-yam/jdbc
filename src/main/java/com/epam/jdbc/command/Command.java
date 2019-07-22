package com.epam.jdbc.command;

/**
 * Enum of existing commands
 */
public enum Command {
    /**
     * Enum value for CreateEmployeeCommand
     */
    MAINPAGE {
        {
            this.command = new MainPageCommand();
        }
    },
    CREATEEMPLOYEE {
        {
            this.command = new CreateEmployeeCommand();
        }
    };
    
    /**
     * Command
     */
    protected ActionCommand command;
    
    /**
     * Command getter
     *
     * @return {@link ActionCommand}
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}