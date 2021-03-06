package com.kk.bookface.infrastructure.friendship;


import com.kk.bookface.domain.friendship.FriendshipQueryDto;
import com.kk.bookface.domain.person.PersonBasicInfoQueryDto;
import com.kk.bookface.domain.shared.exceptions.FriendshipNotFoundException;
import com.kk.bookface.infrastructure.shared.entities.FriendshipEntity;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FriendshipService {

    private FriendshipRepository repository;

    private final FriendshipFactory factory = new FriendshipFactory();

    public List<FriendshipEntity> getAllFriendships() {
        return repository.findAll();
    }
    public FriendshipQueryDto findFriendshipById(Long id) {
        return factory.entitytoQueryDto( repository.findById(id).orElseThrow(() -> new FriendshipNotFoundException(id)) );
    }

    public Long addFriendship(PersonEntity personInviting, PersonEntity personAccepting) {
        FriendshipEntity entity = factory.toFriendshipEntity(personInviting, personAccepting);
        return repository.save( entity ).getFriendshipId();
    }

    public List<PersonBasicInfoQueryDto> getAllFriendsOfPerson(PersonEntity person){
        List<FriendshipEntity> friendships = repository.findFriendshipEntitiesByPersonAcceptingEqualsOrPersonInvitingEquals(person, person);

        return friendships.stream()
                .map( f -> factory.friendshipToFriendOfPerson(f, person))
                .collect(Collectors.toList());
    }

}
