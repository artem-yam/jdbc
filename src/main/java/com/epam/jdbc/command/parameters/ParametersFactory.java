package com.epam.jdbc.command.parameters;

import com.epam.jdbc.command.ActionCommand;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Parameters factory
 */
public class ParametersFactory {
    /**
     * Index of parameter value
     */
    public static final int PARAMETER_VALUE_INDEX = 0;
    /**
     * Command using parameters
     */
    private ActionCommand command;
    
    /**
     * Factory constructor
     *
     * @param command {@link ActionCommand} using parameters
     */
    public ParametersFactory(ActionCommand command) {
        this.command = command;
    }
    
    /**
     * Defines which parameters to create
     *
     * @param requestParameters request parameters to fill {@link CommandParameters} object
     * @return ready {@link CommandParameters} object
     * @throws IllegalAccessException error while getting parameters object or setting it fields
     * @throws InstantiationException error while getting parameters object
     */
    public CommandParameters defineParameters(
        Map<String, String[]> requestParameters)
        throws IllegalAccessException, InstantiationException {
        
        Class paramsClass = CommandParameters.class;
        CommandParameters params =
            (CommandParameters) paramsClass.newInstance();
        
        List<Field> fields = new ArrayList<>();
        
        do {
            fields.addAll(Arrays.asList(paramsClass.getDeclaredFields()));
            
            paramsClass = paramsClass.getSuperclass();
        }
        while (paramsClass != null);
        
        for (Field field : fields) {
            if (requestParameters.containsKey(field.getName())) {
                field.setAccessible(true);
                field.set(params,
                    requestParameters
                        .get(field.getName())[PARAMETER_VALUE_INDEX]);
            }
        }
        
        return params;
    }
}
