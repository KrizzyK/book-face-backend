package com.kk.bookface.domain.shared.exceptions;

public class PeopleBlockNotFoundException extends RuntimeException {

    public PeopleBlockNotFoundException(Long blockId) {
        super("Block not found for id = " + blockId);
    }
}
