package com.kk.bookface.infrastructure.person.endpoint;

import com.kk.bookface.domain.person.PersonQueryDto;
import com.kk.bookface.domain.shared.exceptions.PersonNotFoundException;
import com.kk.bookface.infrastructure.person.PersonFacade;
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
public class PersonController {

    private final PersonFacade personFacade;

    @GetMapping("/person")
    public List<PersonQueryDto> getAllPeople() {
        return personFacade.getAllPeople();
    }

    @RequestMapping(
            path = "/bookface/getPersonById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<PersonQueryDto> findPersonById(@RequestParam Long personId){
        log.info("GET /bookface/getPerson?personId={}", personId );
        final PersonQueryDto queryDto;
        try{
            queryDto = personFacade.findPersonById(personId);
            return ResponseEntity.status(HttpStatus.OK).body(queryDto);
        }catch(PersonNotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(
            path = "/bookface/addPerson",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<String> addPerson(@RequestBody PersonQueryDto queryDto){
        log.info("POST /bookface/addPerson -> " + queryDto.toString() );
        try{
            Long createdPersonId = personFacade.addPerson(queryDto);
            log.info("Person saved with new id = {}", createdPersonId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Person saved with new id = "+ createdPersonId);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
