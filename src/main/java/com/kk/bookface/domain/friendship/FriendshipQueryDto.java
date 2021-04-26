package com.kk.bookface.domain.friendship;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class FriendshipQueryDto {
    private Long personInvitingId;
    private Long personAcceptingId;
    private Timestamp friendshipBegan;
}
