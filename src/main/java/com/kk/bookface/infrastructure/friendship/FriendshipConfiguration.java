package com.kk.bookface.infrastructure.friendship;

import com.kk.bookface.infrastructure.person.PersonFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FriendshipConfiguration {

    @Bean
    FriendshipFacade friendshipFacade(FriendshipRepository repository, PersonFacade personFacade){
        return new FriendshipFacade(repository, personFacade);
    }
}
