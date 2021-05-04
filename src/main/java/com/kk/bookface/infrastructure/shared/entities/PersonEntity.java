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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;

    @Column(name="account_creation_date")
    private Timestamp accountCreationDate;

    @OneToMany(mappedBy = "personInviting", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendshipEntity> friendshipsStarted;

    @OneToMany(mappedBy = "personAccepting", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendshipEntity> friendshipsAccepted;

}
