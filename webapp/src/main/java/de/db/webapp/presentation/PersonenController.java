package de.db.webapp.presentation;


import de.db.webapp.presentation.dtos.PersonDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/v1/personen")
public class PersonenController {





    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> getPerson(@PathVariable  String id) {

        if("1111".equals(id))
            return ResponseEntity.notFound().build();

        final PersonDto person =
                PersonDto
                        .builder()
                        .id(id)
                        .vorname("John")
                        .nachname("Doe")
                        .build();
        return ResponseEntity.ok(person);
        // return ResponseEntity.notFound().build();
    }


    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity< Iterable<PersonDto> > getAll(
            @RequestParam(required = false, defaultValue = "%") String vorname,
            @RequestParam(required = false, defaultValue = "%") String nachname
    ) {

        List list = List.of( PersonDto
                .builder()
                .id("1")
                .vorname("John")
                .nachname("Doe")
                .build(),
                PersonDto
                        .builder()
                        .id("2")
                        .vorname("John")
                        .nachname("Wayne")
                        .build(),
                PersonDto
                        .builder()
                        .id("3")
                        .vorname("John")
                        .nachname("Rambo")
                        .build(),
                PersonDto
                        .builder()
                        .id("4")
                        .vorname("John")
                        .nachname("Wick")
                        .build(),
                PersonDto
                        .builder()
                        .id("5")
                        .vorname("John")
                        .nachname("McClain")
                        .build(),
                PersonDto
                        .builder()
                        .id("6")
                        .vorname("John Boy")
                        .nachname("Waltone")
                        .build()
                );


        return ResponseEntity.ok(list);
        // return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id){
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePerson(@RequestBody PersonDto person){
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdatePerson(@RequestBody  @Valid PersonDto person){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdatePersonNotIdempotent(@RequestBody PersonDto person, final UriComponentsBuilder builder){
        person.setId(UUID.randomUUID().toString());
        final var uri = builder.path("/v1/personen/{id}").buildAndExpand(person.getId());
        return ResponseEntity.created(uri.toUri()).build();
    }


}
