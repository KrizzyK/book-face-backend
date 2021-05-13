package com.kk.bookface.domain.person;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonBasicInfoQueryDto {
    private String name;
    private String surname;
}
