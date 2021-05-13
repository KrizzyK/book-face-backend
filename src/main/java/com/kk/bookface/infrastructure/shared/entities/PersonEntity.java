package com.kk.bookface.infrastructure.shared.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="bookface_person")
public class PersonEntity implements Serializable {

    @Id
    @Column(name="person_id", nullable = false, unique = true)
    @SequenceGenerator(name = "person_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    private Long personId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;

    @Column(name="bio", nullable = true)
    private String bio;

    @Column(name="user_unique_id", nullable = false, unique = true)
    private String userUniqueId;

    @Column(name="account_creation_date")
    private Timestamp accountCreationDate;

    @OneToMany(mappedBy = "personInviting", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendshipEntity> friendshipsStarted;

    @OneToMany(mappedBy = "personAccepting", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendshipEntity> friendshipsAccepted;

}
