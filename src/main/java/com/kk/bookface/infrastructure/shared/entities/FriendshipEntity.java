package com.kk.bookface.infrastructure.shared.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="bookface_friendship")
public class FriendshipEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "friendship_id", nullable = false, unique = true)
    private Long friendshipId;

    @Column(name= "friendhip_began", nullable = false)
    private Timestamp friendshipBegan;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_inviting", nullable = false)
    private PersonEntity personInviting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_accepting", nullable = true)
    private PersonEntity personAccepting;

}
