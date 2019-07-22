package com.epam.jdbc.command;

import com.epam.jdbc.command.dto.TransitionInformation;

import javax.servlet.http.HttpServletRequest;

/**
 * Command interface
 */
public interface ActionCommand {
    
    /**
     * Executes current command
     *
     * @return {@link TransitionInformation}
     */
    TransitionInformation execute(HttpServletRequest req);
}