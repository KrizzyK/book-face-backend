package com.kk.bookface.domain.shared.exceptions;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(Long personId) {
        super("Person not found for id = " + personId);
    }
}
