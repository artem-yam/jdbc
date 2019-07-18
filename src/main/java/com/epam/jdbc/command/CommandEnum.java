package com.epam.jdbc.command;

/**
 * Enum of existing commands
 */
public enum CommandEnum {
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
    ActionCommand command;

    /**
     * Command getter
     *
     * @return {@link ActionCommand}
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}