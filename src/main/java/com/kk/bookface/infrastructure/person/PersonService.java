package com.kk.bookface.infrastructure.person;

import com.kk.bookface.domain.person.AddPersonQueryDto;
import com.kk.bookface.domain.person.PersonBasicInfoQueryDto;
import com.kk.bookface.domain.person.PersonProfileQueryDto;
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

    public List<PersonBasicInfoQueryDto> getAllPeople() {
        return personRepository
                .findAll()
                .stream()
                .map(ent -> factory.entityToPersonBasicInfoQueryDto(ent))
                .collect(Collectors.toList());
    }

    public PersonProfileQueryDto findPersonById(Long id) {
        return factory.entityToPersonProfileQueryDto( personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id)) );
    }

    public Long addPerson(AddPersonQueryDto dto) {
        String userUniqueId = dto.getName() + "_" + dto.getSurname();
        userUniqueId += personRepository.countAllByNameAndSurname(dto.getName(), dto.getSurname());

        PersonEntity entity = factory.addPersonQueryDtoToEntity(dto, userUniqueId);
        return personRepository.save(entity).getPersonId();
    }

    public List<PersonEntity> getPersonByNameAndSurname(String name, String surname) {
        return personRepository.findPeopleByNameAndSurname(name, surname).orElseThrow(() -> new PersonNotFoundException(name, surname));
    }

    public PersonEntity findPersonEntityById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
