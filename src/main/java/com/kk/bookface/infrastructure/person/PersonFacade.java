package com.kk.bookface.infrastructure.person;

import com.kk.bookface.domain.person.AddPersonQueryDto;
import com.kk.bookface.domain.person.PersonBasicInfoQueryDto;
import com.kk.bookface.domain.person.PersonProfileQueryDto;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PersonFacade {
    public PersonFacade(PersonRepository repository) {
        this.personService = new PersonService(repository);
    }


    private final PersonService personService;

    public PersonProfileQueryDto findPersonById(Long id) {
        return personService.findPersonById(id);
    }

    //TODO
    public List<PersonBasicInfoQueryDto> getAllPeople() {
        return personService.getAllPeople();
    }

    public Long addPerson(AddPersonQueryDto dto) {
        return personService.addPerson(dto);
    }

    public PersonEntity findPersonEntityById(Long id) {
        return personService.findPersonEntityById(id);
    }

    public PersonProfileQueryDto findPersonProfileByUniqueId(String uniqueId) {
        return personService.findPersonProfileByUniqueId(uniqueId);
    }
}
