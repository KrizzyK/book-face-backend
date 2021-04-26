package com.kk.bookface.infrastructure.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfiguration {

    @Bean
    PersonFacade personFacade(PersonRepository repository){
        return new PersonFacade(repository);
    }
}
