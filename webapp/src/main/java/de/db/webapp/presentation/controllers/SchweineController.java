package de.db.webapp.presentation.controllers;


import de.db.webapp.presentation.mapper.SchweinDtoMapper;
import de.db.webapp.presentation.dtos.SchweinDto;
import de.db.webapp.services.SchweineServiceException;
import de.db.webapp.services.SchweineService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/schweine")
@AllArgsConstructor
public class SchweineController {

   private final SchweineService service;
   private final SchweinDtoMapper mapper;


    @ApiResponse(responseCode = "200", description = "Schwein wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Schwein wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<SchweinDto> getSchwein(@PathVariable  String id) throws SchweineServiceException{

        return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));

    }


    @ApiResponse(responseCode = "200", description = "Schwein wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Schwein wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity< Iterable<SchweinDto> > getAll(
            @RequestParam(required = false, defaultValue = "%") String name,
            @RequestParam(required = false, defaultValue = "10") double gewicht
    ) throws SchweineServiceException{
           return ResponseEntity.ok(mapper.convert(service.findeAll()));
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteSchwein(@PathVariable String id){
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSchwein(@RequestBody SchweinDto schwein) throws SchweineServiceException{
        if(service.loeschen(mapper.convert(schwein)))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdateSchwein(@RequestBody  @Valid SchweinDto schwein) throws SchweineServiceException {

        if(service.speichern(mapper.convert(schwein))){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path="/{id}/fuettern")
    public ResponseEntity<Void> fuettereSchwein(@PathVariable String id) throws SchweineServiceException {
        if(service.fuettern(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
