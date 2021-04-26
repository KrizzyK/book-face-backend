package com.kk.bookface.infrastructure.person;

import com.kk.bookface.domain.person.PersonQueryDto;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;

public class PersonFactory {

    PersonEntity personQueryDtoToEntity(PersonQueryDto dto) {
        return PersonEntity.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .build();
    }
    PersonQueryDto entityToPersonQueryDto(PersonEntity entity) {
        return PersonQueryDto.builder()
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }

}
