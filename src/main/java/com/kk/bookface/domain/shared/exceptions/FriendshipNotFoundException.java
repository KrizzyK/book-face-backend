package com.kk.bookface.domain.shared.exceptions;

public class FriendshipNotFoundException extends RuntimeException{

    public FriendshipNotFoundException(Long friendshipId) {
        super("Friendship not found for id = " + friendshipId);
    }
}
