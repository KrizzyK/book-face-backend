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
    @SequenceGenerator(name = "friendship_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friendship_id_seq")
    @Column(name= "friendship_id", nullable = false, unique = true)
    private Long friendshipId;

    @Column(name= "friendship_began")
    private Timestamp friendshipBegan;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_inviting", nullable = false)
    private PersonEntity personInviting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_accepting", nullable = true)
    private PersonEntity personAccepting;

}
