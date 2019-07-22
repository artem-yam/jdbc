package com.epam.jdbc.command.parameters;

public class CreateEmployeeParameters implements Parameters {
    
    private String firstName;
    private String lastName;
    
    public CreateEmployeeParameters() {
    }
    
    public CreateEmployeeParameters(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
}
