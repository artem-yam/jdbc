package com.epam.jdbc.command;

import com.epam.jdbc.GoToPageHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Command interface
 */
public interface ActionCommand {

    /**
     * Executes current command
     *
     * @param request request for processing
     * @return {@link GoToPageHandler}
     */
    GoToPageHandler execute(HttpServletRequest request);
}