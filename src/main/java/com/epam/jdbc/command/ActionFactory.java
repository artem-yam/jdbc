package com.epam.jdbc.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Command factory
 */
public class ActionFactory {
    /**
     * Logger
     */
    private static final Logger logger = LogManager
            .getLogger(new Object() {
            }.getClass().getEnclosingClass());

    /**
     * Defines which command to create
     * @param request request for processing
     * @return {@link ActionCommand}
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand command = new MainPageCommand();

        String action = request.getParameter("command");

        if (action != null && !action.isEmpty()) {
            try {
                CommandEnum currentEnum =
                        CommandEnum.valueOf(action.toUpperCase());
                command = currentEnum.getCurrentCommand();
            } catch (IllegalArgumentException illegalArgumentException) {
                logger.debug("Command wasn't found", illegalArgumentException);
            }
        }

        logger.debug("Returning command {}", command.getClass());

        return command;
    }
}