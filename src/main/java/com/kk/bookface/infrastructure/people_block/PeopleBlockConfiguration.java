package com.kk.bookface.infrastructure.people_block;

import com.kk.bookface.infrastructure.friendship.FriendshipFacade;
import com.kk.bookface.infrastructure.friendship.FriendshipRepository;
import com.kk.bookface.infrastructure.person.PersonFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PeopleBlockConfiguration {

    @Bean
    PeopleBlockFacade peopleBlockFacade(PeopleBlockRepository repository, PersonFacade personFacade){
        return new PeopleBlockFacade(repository, personFacade);
    }
}
