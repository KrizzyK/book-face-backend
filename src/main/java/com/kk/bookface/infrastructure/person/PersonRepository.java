package com.kk.bookface.infrastructure.person;

import com.kk.bookface.infrastructure.shared.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Optional<List<PersonEntity>> findPeopleByNameAndSurname(String name, String surname);

    long countAllByNameAndSurname(String name, String surname);
}
