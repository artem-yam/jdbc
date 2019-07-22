package com.epam.jdbc.command.parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Command factory
 */
public class ParametersFactory {
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
     * @param parameters
     * @return
     */
    public Parameters defineParameters(Map<String, String[]> parameters) {
        Parameters params = null;
        
        //todo COMMAND_PARAMETER.equalsIgnoreCase()
        if (parameters.containsKey(COMMAND_PARAMETER)) {
            String[] command = parameters.get(COMMAND_PARAMETER);
            
            logger.info("command param {}", command);
            
            if (command != null && !command[0].isEmpty()) {
                
                CreateEmployeeParameters paramInstance =
                    new CreateEmployeeParameters();
                
                try {
                    for (Field field : paramInstance.getClass()
                                           .getDeclaredFields()) {
                        if (parameters.containsKey(field.getName())) {
                            field.set(paramInstance,
                                parameters.get(field.getName()));
                        }
                        
                        params = paramInstance;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                
            }
        }
        
        return params;
    }
}