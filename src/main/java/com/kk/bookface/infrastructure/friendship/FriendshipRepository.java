package com.kk.bookface.infrastructure.friendship;

import com.kk.bookface.infrastructure.shared.entities.FriendshipEntity;
import com.kk.bookface.infrastructure.shared.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> {

    List<FriendshipEntity> findFriendshipEntitiesByPersonAcceptingEqualsOrPersonInvitingEquals(PersonEntity personAccepting, PersonEntity personInviting);
}
