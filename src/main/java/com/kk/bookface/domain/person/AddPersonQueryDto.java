package com.kk.bookface.domain.person;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddPersonQueryDto {
    private String name;
    private String surname;
    private String bio;
    private String profilePhotoUrl;
}
