package de.db.webapp.presentation;


import de.db.webapp.presentation.dtos.PersonDto;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@RequestMapping("/v1/personen")
@AllArgsConstructor
public class PersonenController {

   private final PersonenService service;
   private final PersonDtoMapper mapper;



    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> getPerson(@PathVariable  String id) throws PersonenServiceException{

        return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));

    }


    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity< Iterable<PersonDto> > getAll(
            @RequestParam(required = false, defaultValue = "%") String vorname,
            @RequestParam(required = false, defaultValue = "%") String nachname
    ) throws PersonenServiceException{

           return ResponseEntity.ok(mapper.convert(service.findeAll()));

    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id){
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePerson(@RequestBody PersonDto person) throws PersonenServiceException{
        if(service.loeschen(mapper.convert(person)))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdatePerson(@RequestBody  @Valid PersonDto person) throws PersonenServiceException {

        if(service.speichern(mapper.convert(person))){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


//    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> saveOrUpdatePersonNotIdempotent(@RequestBody PersonDto person, final UriComponentsBuilder builder){
//        person.setId(UUID.randomUUID().toString());
//        final var uri = builder.path("/v1/personen/{id}").buildAndExpand(person.getId());
//        return ResponseEntity.created(uri.toUri()).build();
//    }


}
