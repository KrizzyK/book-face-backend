package com.kk.bookface.infrastructure.friendship;

import com.kk.bookface.domain.friendship.FriendshipQueryDto;
import com.kk.bookface.infrastructure.shared.entities.FriendshipEntity;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;

import java.time.LocalDateTime;

public class FriendshipFactory {

    FriendshipEntity toFriendshipEntity(PersonEntity inviting, PersonEntity accepting) {
        return FriendshipEntity.builder()
                .personAccepting(accepting)
                .personInviting(inviting)
                .build();
    }

    FriendshipQueryDto entitytoQueryDto(FriendshipEntity entity) {
        return FriendshipQueryDto.builder()
                .friendshipBegan(entity.getFriendshipBegan())
                .personAcceptingId(entity.getPersonAccepting().getPersonId())
                .personInvitingId(entity.getPersonInviting().getPersonId())
                .build();
    }
}
