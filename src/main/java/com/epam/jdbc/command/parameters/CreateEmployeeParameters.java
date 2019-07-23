package com.epam.jdbc.command.parameters;

public class CreateEmployeeParameters extends CommandParameters {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
