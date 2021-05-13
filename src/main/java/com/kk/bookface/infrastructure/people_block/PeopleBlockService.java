package com.kk.bookface.infrastructure.people_block;

import com.kk.bookface.domain.people_block.PeopleBlockQueryDto;
import com.kk.bookface.domain.shared.exceptions.PeopleBlockNotFoundException;
import com.kk.bookface.infrastructure.shared.entities.PeopleBlockEntity;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PeopleBlockService {

    private PeopleBlockRepository repository;

    private final PeopleBlockFactory factory = new PeopleBlockFactory();

    public PeopleBlockQueryDto getPeopleBlockById(Long blockId) {
        return factory.entityToPeopleBlockQueryDto( repository.findById(blockId).orElseThrow( () -> new PeopleBlockNotFoundException(blockId)  ) );
    }

    public Long addPeopleBlock(PeopleBlockQueryDto dto, PersonEntity personBlocking, PersonEntity personBlocked) {
        PeopleBlockEntity block = factory.peopleBlockQueryDtoToEntity(dto, personBlocking, personBlocked);
        return repository.save(block).getBlockId();
    }
}
