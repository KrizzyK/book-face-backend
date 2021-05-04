package com.kk.bookface.infrastructure.people_block;

import com.kk.bookface.domain.people_block.PeopleBlockQueryDto;
import com.kk.bookface.infrastructure.shared.entities.PeopleBlockEntity;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;

public class PeopleBlockFactory {

    public PeopleBlockQueryDto entityToPeopleBlockQueryDto(PeopleBlockEntity entity) {
        return PeopleBlockQueryDto.builder()
                .personBlockingId(entity.getPersonBlocking().getPersonId())
                .personBlockedId(entity.getPersonBlocked().getPersonId())
                .build();
    }

    public PeopleBlockEntity peopleBlockQueryDtoToEntity(PeopleBlockQueryDto dto, PersonEntity personBlocking, PersonEntity personBlocked) {
        return PeopleBlockEntity.builder()
                .personBlocking(personBlocking)
                .personBlocked(personBlocked)
                .build();
    }
}
