package com.kk.bookface.domain.people_block;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeopleBlockQueryDto {
    private Long personBlockingId;
    private Long personBlockedId;
}
