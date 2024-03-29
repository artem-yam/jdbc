package com.epam.jdbc.command.parameters;

/**
 * Parameters used in command
 */
public class CommandParameters {
    
    /**
     * New employee last name
     */
    private String lastName;
    /**
     * New employee first name
     */
    private String firstName;
    
    /**
     * First name getter
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Last name getter
     *
     * @return Last name
     */
    public String getLastName() {
        return lastName;
    }
}
