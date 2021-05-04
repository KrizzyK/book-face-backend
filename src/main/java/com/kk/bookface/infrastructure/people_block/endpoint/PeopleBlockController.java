package com.kk.bookface.infrastructure.people_block.endpoint;

import com.kk.bookface.domain.people_block.PeopleBlockQueryDto;
import com.kk.bookface.domain.shared.exceptions.PeopleBlockNotFoundException;
import com.kk.bookface.infrastructure.people_block.PeopleBlockFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
public class PeopleBlockController {

    private final PeopleBlockFacade peopleBlockFacade;

    @RequestMapping(
            path = "/bookface/getPeopleBlockById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<PeopleBlockQueryDto> findPeopleBlockById(@RequestParam Long blockId){
        log.info("GET /bookface/getPeopleBlockById?blockId={}", blockId );
        final PeopleBlockQueryDto queryDto;
        try{
            queryDto = peopleBlockFacade.getPeopleBlockById(blockId);
            return ResponseEntity.status(HttpStatus.OK).body(queryDto);
        }catch(PeopleBlockNotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @RequestMapping(
            path = "/bookface/addPeopleBlock",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<String> addPeopleBlock(@RequestBody PeopleBlockQueryDto queryDto){
        log.info("POST /bookface/addPeopleBlock -> " + queryDto.toString() );
        try{
            Long createdBlockId = peopleBlockFacade.addPeopleBlock(queryDto);
            log.info("Block saved with new id = {}", createdBlockId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Block saved with new id = "+ createdBlockId);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
