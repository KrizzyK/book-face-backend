package com.kk.bookface.infrastructure.person.endpoint;

import com.kk.bookface.domain.person.AddPersonQueryDto;
import com.kk.bookface.domain.person.PersonBasicInfoQueryDto;
import com.kk.bookface.domain.person.PersonProfileQueryDto;
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
@CrossOrigin (maxAge= 3600, origins="*")
public class PersonController {

    private final PersonFacade personFacade;

    @GetMapping("/person")
    public List<PersonBasicInfoQueryDto> getAllPeople() {
        return personFacade.getAllPeople();
    }

    @RequestMapping(
            path = "/bookface/getPersonById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<PersonProfileQueryDto> findPersonById(@RequestParam Long personId){
        log.info("GET /bookface/getPersonById?personId={}", personId );
        final PersonProfileQueryDto queryDto;
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
            path = "/bookface/getPersonByUniqueId",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<PersonProfileQueryDto> findPersonByUniqueId(@RequestParam String personUniqueId){
        log.info("GET /bookface/getPersonByUniqueId?personUniqueId={}", personUniqueId );
        final PersonProfileQueryDto queryDto;
        try{
            queryDto = personFacade.findPersonProfileByUniqueId(personUniqueId);
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
    ResponseEntity<String> addPerson(@RequestBody AddPersonQueryDto queryDto){
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
