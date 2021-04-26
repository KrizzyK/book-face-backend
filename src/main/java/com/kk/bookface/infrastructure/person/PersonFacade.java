package com.kk.bookface.infrastructure.person;

import com.kk.bookface.domain.person.PersonQueryDto;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PersonFacade {
    public PersonFacade(PersonRepository repository) {
        this.personService = new PersonService(repository);
    }


    private final PersonService personService;

    public PersonQueryDto findPersonById(Long id) {
        return personService.findPersonById(id);
    }

    public List<PersonQueryDto> getAllPeople() {
        return personService.getAllPeople();
    }

    public Long addPerson(PersonQueryDto dto) {
        return personService.addPerson(dto);
    }

    public PersonEntity findPersonEntityById(Long id) {
        return personService.findPersonEntityById(id);
    }
}
