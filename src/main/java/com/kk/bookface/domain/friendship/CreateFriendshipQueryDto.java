package com.kk.bookface.domain.friendship;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateFriendshipQueryDto {
    private Long personInvitingId;
    private Long personAcceptingId;
}
