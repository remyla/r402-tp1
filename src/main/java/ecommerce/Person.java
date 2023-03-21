package ecommerce;

import java.util.Objects;

public class Person extends UniqueObject {

    private final String firstName;

    private final String lastName;

    protected Person(String firstName, String lastName) {
        super();
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
