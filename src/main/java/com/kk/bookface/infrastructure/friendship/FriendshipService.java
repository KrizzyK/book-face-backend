package com.kk.bookface.infrastructure.friendship;


import com.kk.bookface.domain.friendship.FriendshipQueryDto;
import com.kk.bookface.domain.shared.exceptions.FriendshipNotFoundException;
import com.kk.bookface.infrastructure.shared.entities.FriendshipEntity;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendshipService {

    private FriendshipRepository repository;

    private final FriendshipFactory factory = new FriendshipFactory();

    public List<FriendshipEntity> getAllFriendships() {
        return repository.findAll();
    }
    public FriendshipQueryDto findFriendshipById(Long id) {
        return factory.entitytoQueryDto( repository.findById(id).orElseThrow(FriendshipNotFoundException::new) );
    }

    public Long addFriendship(PersonEntity personInviting, PersonEntity personAccepting) {
        FriendshipEntity entity = factory.toFriendshipEntity(personInviting, personAccepting);
        return repository.save( entity ).getFriendshipId();
    }

}
