package com.epam.jdbc.datalayer.dto;

/**
 * Employee entity
 */
public class Employee {

    /**
     * ID
     */
    private long id;
    /**
     * Last name
     */
    private String lastName;
    /**
     * First name
     */
    private String firstName;


    /**
     * Employee class constructor
     *
     * @param id        ID
     * @param lastName  Last name
     * @param firstName First name
     */
    public Employee(long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    /**
     * ID getter
     *
     * @return ID
     */
    public long getId() {
        return id;
    }

    /**
     * ID setter
     *
     * @param id ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * First name getter
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * First name setter
     *
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name getter
     *
     * @return Last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Last name setter
     *
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
