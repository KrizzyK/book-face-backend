package com.kk.bookface.infrastructure.person;

import com.kk.bookface.domain.person.PersonQueryDto;
import com.kk.bookface.domain.shared.exceptions.PersonNotFoundException;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    private final PersonFactory factory = new PersonFactory();

    public List<PersonQueryDto> getAllPeople() {
        return personRepository
                .findAll()
                .stream()
                .map(ent -> factory.entityToPersonQueryDto(ent))
                .collect(Collectors.toList());
    }

    public PersonQueryDto findPersonById(Long id) {
        return factory.entityToPersonQueryDto( personRepository.findById(id).orElseThrow(PersonNotFoundException::new) );
    }

    public Long addPerson(PersonQueryDto dto) {
        PersonEntity entity = factory.personQueryDtoToEntity(dto);
        return personRepository.save(entity).getPersonId();
    }

    public List<PersonEntity> getPersonByNameAndSurname(String name, String surname) {
        return personRepository.findPeopleByNameAndSurname(name, surname).orElseThrow(PersonNotFoundException::new);
    }

    public PersonEntity findPersonEntityById(Long id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }
}
