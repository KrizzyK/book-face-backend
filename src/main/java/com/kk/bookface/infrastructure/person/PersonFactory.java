package com.kk.bookface.infrastructure.person;

import com.kk.bookface.domain.person.AddPersonQueryDto;
import com.kk.bookface.domain.person.PersonBasicInfoQueryDto;
import com.kk.bookface.domain.person.PersonProfileQueryDto;
import com.kk.bookface.infrastructure.friendship.FriendshipFactory;
import com.kk.bookface.infrastructure.shared.entities.FriendshipEntity;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonFactory {

    private final FriendshipFactory friendshipFactory = new FriendshipFactory();

    PersonEntity addPersonQueryDtoToEntity(AddPersonQueryDto dto, String userUniqueId) {
        return PersonEntity.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .bio(dto.getBio())
                .userUniqueId(userUniqueId)
//                .friendshipsAccepted(new HashSet<FriendshipEntity>())
//                .friendshipsStarted(new HashSet<FriendshipEntity>())
                .build();
    }

    PersonProfileQueryDto entityToPersonProfileQueryDto(PersonEntity person) {
        Set<FriendshipEntity> friendships = person.getFriendshipsAccepted();
        friendships.addAll(person.getFriendshipsStarted());

        List<PersonBasicInfoQueryDto> friends = friendships.stream()
                .map( f -> friendshipFactory.friendshipToFriendOfPerson(f, person))
                .collect(Collectors.toList());

        return PersonProfileQueryDto.builder()
                .name(person.getName())
                .surname(person.getSurname())
                .bio(person.getBio())
                .friends(friends)
                .build();
    }

    PersonBasicInfoQueryDto entityToPersonBasicInfoQueryDto(PersonEntity entity) {
        return PersonBasicInfoQueryDto.builder()
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }




}
