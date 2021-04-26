package com.kk.bookface.infrastructure.friendship;

import com.kk.bookface.domain.friendship.CreateFriendshipQueryDto;
import com.kk.bookface.domain.friendship.FriendshipQueryDto;
import com.kk.bookface.infrastructure.person.PersonFacade;
import com.kk.bookface.infrastructure.shared.entities.FriendshipEntity;

import java.util.List;

public class FriendshipFacade {

    private FriendshipService friendshipService;
    private PersonFacade personFacade;

    public FriendshipFacade(FriendshipRepository friendshipRepository, PersonFacade personFacade) {
        this.friendshipService = new FriendshipService(friendshipRepository);
        this.personFacade = personFacade;
    }

    public List<FriendshipEntity> getAllFriendships() {
        return friendshipService.getAllFriendships();
    }

    public FriendshipQueryDto findFriendshipById(Long id) {
        return friendshipService.findFriendshipById(id);
    }

    public Long addFriendship(CreateFriendshipQueryDto dto) {
        return friendshipService.addFriendship(
                personFacade.findPersonEntityById(dto.getPersonInvitingId()),
                personFacade.findPersonEntityById(dto.getPersonAcceptingId())
        );
    }
}
