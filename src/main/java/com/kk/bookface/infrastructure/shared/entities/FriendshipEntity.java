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
    private Long friendshipId;

    private Timestamp friendshipBegan;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_inviting")
    private PersonEntity personInviting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_accepting")
    private PersonEntity personAccepting;

}
