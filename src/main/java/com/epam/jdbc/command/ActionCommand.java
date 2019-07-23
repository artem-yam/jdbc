package com.epam.jdbc.command;

import com.epam.jdbc.command.dto.TransitionInformation;
import com.epam.jdbc.command.parameters.CommandParameters;

/**
 * Command interface
 */
public interface ActionCommand {
    
    /**
     * Executes current command
     *
     * @return {@link TransitionInformation}
     */
    TransitionInformation execute(CommandParameters parameters);
}