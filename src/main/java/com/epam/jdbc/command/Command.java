package com.epam.jdbc.command;

/**
 * Enum of existing commands
 */
public enum Command {
    /**
     * Enum value for MainPageCommand
     */
    MAINPAGE {
        {
            this.command = new MainPageCommand();
        }
    },
    /**
     * Enum value for CreateEmployeeCommand
     */
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