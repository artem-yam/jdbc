package com.epam.jdbc.command.parameters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines class has some parameters
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HasParameters {
    /**
     * Used parameters class
     *
     * @return parameters class
     */
    Class<? extends CommandParameters> parameters();
}
