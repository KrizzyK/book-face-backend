package com.kk.bookface.domain.shared.exceptions;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(Long personId) {
        super("Person not found for id = " + personId);
    }

    public PersonNotFoundException(String personUniqueId) {
        super("Person not found for unique id = " + personUniqueId);
    }

    public PersonNotFoundException(String name, String surname) {
        super("Person " + name + " " + surname + "not found!");
    }

}
