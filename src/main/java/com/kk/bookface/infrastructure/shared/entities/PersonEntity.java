package com.kk.bookface.infrastructure.shared.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="bookface_person")
public class PersonEntity implements Serializable {

    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    private String name;
    private String surname;

    @OneToMany(mappedBy = "personInviting", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendshipEntity> friendshipsStarted;

    @OneToMany(mappedBy = "personAccepting", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendshipEntity> friendshipsAccepted;

}
