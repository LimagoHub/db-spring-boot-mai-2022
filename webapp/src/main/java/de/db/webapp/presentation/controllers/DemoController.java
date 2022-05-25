package de.db.webapp.presentation.controllers;


import de.db.webapp.Max;
import de.db.webapp.presentation.dtos.PersonDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@Max
public class DemoController {

    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")

    @GetMapping("/gruss")
    public String gruss() {
        return "Hallo Spring";
    }


    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping(path = "/john", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> getJohn() {
        final PersonDto person =
                PersonDto
                .builder()
                .id("1")
                .vorname("John")
                .nachname("Doe")
                .build();
        return ResponseEntity.ok(person);
       // return ResponseEntity.notFound().build();
    }
}
