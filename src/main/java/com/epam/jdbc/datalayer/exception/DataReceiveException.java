package com.epam.jdbc.datalayer.exception;

/**
 * Data storage manipulations exception
 */
public class DataReceiveException extends Throwable {
    
    /**
     * Constructor
     *
     * @param message exception message
     * @param cause   cause of exception
     */
    public DataReceiveException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
