package com.kk.bookface.infrastructure.shared.entities;


import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="bookface_people_block")
public class PeopleBlockEntity {

    @Id
    @Column(name="block_id", nullable = false, unique = true)
    @SequenceGenerator(name = "people_block_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "people_block_id_seq")
    private Long blockId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_blocking", nullable = false)
    private PersonEntity personBlocking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_blocked", nullable = false)
    private PersonEntity personBlocked;

}
