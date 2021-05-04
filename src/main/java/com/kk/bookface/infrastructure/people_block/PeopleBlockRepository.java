package com.kk.bookface.infrastructure.people_block;

import com.kk.bookface.infrastructure.shared.entities.PeopleBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleBlockRepository extends JpaRepository<PeopleBlockEntity, Long> {
}
