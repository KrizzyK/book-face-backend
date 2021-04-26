package com.kk.bookface.infrastructure.friendship.endpoint;

import com.kk.bookface.domain.friendship.CreateFriendshipQueryDto;
import com.kk.bookface.domain.friendship.FriendshipQueryDto;
import com.kk.bookface.domain.person.PersonQueryDto;
import com.kk.bookface.domain.shared.exceptions.FriendshipNotFoundException;
import com.kk.bookface.domain.shared.exceptions.PersonNotFoundException;
import com.kk.bookface.infrastructure.friendship.FriendshipFacade;
import com.kk.bookface.infrastructure.person.PersonFacade;
import com.kk.bookface.infrastructure.shared.entities.FriendshipEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@AllArgsConstructor
@RestController
public class FriendshipController {

    private final FriendshipFacade friendshipFacade;

    @GetMapping("/friendship")
    public List<FriendshipEntity> getAllFriendship() {
        return friendshipFacade.getAllFriendships();
    }

    @RequestMapping(
            path = "/bookface/getFriendshipById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<FriendshipQueryDto> findFriendshipById(@RequestParam Long friendshipId){
        log.info("GET /bookface/getFriendshipById?friendshipId={}", friendshipId );
        final FriendshipQueryDto queryDto;
        try{
            queryDto = friendshipFacade.findFriendshipById(friendshipId);
            return ResponseEntity.status(HttpStatus.OK).body(queryDto);
        }catch(FriendshipNotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(
            path = "/bookface/addFriendship",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<String> addFriendship(@RequestBody CreateFriendshipQueryDto queryDto){
        log.info("POST /bookface/addFriendship -> " + queryDto.toString() );
        try{
            Long createdFriendshipId = friendshipFacade.addFriendship(queryDto);
            log.info("Friendship saved with new id = {}", createdFriendshipId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Friendship saved with new id = "+ createdFriendshipId);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
