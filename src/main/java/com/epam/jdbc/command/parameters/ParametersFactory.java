package com.epam.jdbc.command.parameters;

import com.epam.jdbc.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParametersFactory {

    private ActionCommand command;

    public ParametersFactory(ActionCommand command) {
        this.command = command;
    }

    public CommandParameters defineParameters(HttpServletRequest request)
            throws IllegalAccessException, InstantiationException {

        CommandParameters params = (CommandParameters) command.getClass()
                .getAnnotation(HasParameters.class)
                .parameters().newInstance();

        params.setSession(request.getSession());

        List<Field> fields = new ArrayList<>();

        Class paramsClass = params.getClass();

        do {
            fields.addAll(Arrays.asList(paramsClass.getDeclaredFields()));

            paramsClass = paramsClass.getSuperclass();
        }
        while (paramsClass != null);

        for (Field field : fields) {
            if (request.getParameterMap().containsKey(field.getName())) {
                field.setAccessible(true);
                field.set(params,
                        request.getParameterMap().get(field.getName())[0]);
            }
        }

        return params;
    }
}
