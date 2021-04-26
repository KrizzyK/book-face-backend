package com.kk.bookface.infrastructure.friendship;

import com.kk.bookface.infrastructure.shared.entities.FriendshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> {
}
