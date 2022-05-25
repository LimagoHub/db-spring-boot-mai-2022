package de.db.webapp.presentation.controllers;


import de.db.webapp.ports.PersonenHandler;
import de.db.webapp.presentation.mapper.PersonDtoMapper;
import de.db.webapp.presentation.dtos.PersonDto;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.PersonenServiceImpl;
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
public class PersonenCommandController {

    private PersonenHandler handler;




    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id) throws PersonenServiceException{

        handler.handleDelete(id);
        return ResponseEntity.ok().build();

    }




    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdatePerson(@RequestBody  @Valid PersonDto person) throws PersonenServiceException {

        handler.handleSpeichern(person);
        return ResponseEntity.ok().build();

    }




}
