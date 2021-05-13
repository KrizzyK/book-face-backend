package com.kk.bookface.domain.person;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonProfileQueryDto {
    private String name;
    private String surname;
    private String bio;

    //friends
    List<PersonBasicInfoQueryDto> friends;

    //posts
}
