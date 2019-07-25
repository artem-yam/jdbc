package com.epam.jdbc.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Command factory
 */
public class ActionFactory {
    /**
     * Index of parameter value
     */
    public static final int PARAMETER_VALUE_INDEX = 0;
    /**
     * Logger
     */
    private static final Logger logger = LogManager
                                             .getLogger(new Object() {
                                             }.getClass().getEnclosingClass());
    /**
     * Command parameter name
     */
    private static final String COMMAND_PARAMETER = "command";
    
    /**
     * Defines which command to create
     *
     * @param requestParameters request parameters
     * @return {@link ActionCommand}
     */
    public ActionCommand defineCommand(
        Map<String, String[]> requestParameters) {
        ActionCommand command = new MainPageCommand();
        
        if (requestParameters.containsKey(COMMAND_PARAMETER)) {
            
            String action =
                requestParameters.get(COMMAND_PARAMETER)[PARAMETER_VALUE_INDEX];
            
            if (action != null && !action.isEmpty()) {
                try {
                    Command currentEnum =
                        Command.valueOf(action.toUpperCase());
                    command = currentEnum.getCurrentCommand();
                } catch (IllegalArgumentException illegalArgumentException) {
                    logger.debug("Unknown command ", illegalArgumentException);
                }
            }
        }
        
        logger.debug("Returning command {}", command.getClass());
        
        return command;
    }
}