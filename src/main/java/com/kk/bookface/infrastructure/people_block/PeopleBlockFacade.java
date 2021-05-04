package com.kk.bookface.infrastructure.people_block;

import com.kk.bookface.domain.people_block.PeopleBlockQueryDto;
import com.kk.bookface.infrastructure.person.PersonFacade;

public class PeopleBlockFacade {

    private PeopleBlockService peopleBlockService;
    private PersonFacade personFacade;

    public PeopleBlockFacade(PeopleBlockRepository repository, PersonFacade personFacade) {
        this.peopleBlockService = new PeopleBlockService(repository);
        this.personFacade = personFacade;
    }

    public PeopleBlockQueryDto getPeopleBlockById(Long blockId) {
        return peopleBlockService.getPeopleBlockById(blockId);
    }

    public Long addPeopleBlock(PeopleBlockQueryDto dto){
        return peopleBlockService.addPeopleBlock(
                dto,
                personFacade.findPersonEntityById(dto.getPersonBlockingId()),
                personFacade.findPersonEntityById(dto.getPersonBlockingId())
        );
    }

}
