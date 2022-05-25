package de.db.webapp.presentation;

import de.db.webapp.presentation.dtos.PersonDto;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql({"/create.sql", "/insert.sql"})
@ExtendWith(SpringExtension.class)
class PersonenControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService serviceMock;
    @Test
    void test1() throws Exception{

        Person validPerson = Person.builder().id("1").vorname("john").nachname("doe").build();

        when(serviceMock.findeNachId(anyString())).thenReturn(Optional.of(validPerson));

        final PersonDto result = restTemplate.getForObject("/v1/personen/4711",PersonDto.class);
        assertEquals("john", result.getVorname());

    }

    @Test
    void test2() throws Exception{

        Person validPerson = Person.builder().id("1").vorname("john").nachname("doe").build();

        when(serviceMock.findeNachId(anyString())).thenReturn(Optional.of(validPerson));

        final String result = restTemplate.getForObject("/v1/personen/4711",String.class);
        System.out.println(result);
    }

    @Test
    void test3() throws Exception{

        Person validPerson = Person.builder().id("1").vorname("john").nachname("doe").build();

        when(serviceMock.findeNachId(anyString())).thenReturn(Optional.of(validPerson));

        final ResponseEntity<PersonDto> result = restTemplate.getForEntity("/v1/personen/4711",PersonDto.class);
        PersonDto person = result.getBody();
        assertEquals("john", person.getVorname());
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void test4() throws Exception{



        when(serviceMock.findeNachId(anyString())).thenReturn(Optional.empty());

        final ResponseEntity<PersonDto> result = restTemplate.getForEntity("/v1/personen/4711",PersonDto.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

    }

    @Test
    void test5() throws Exception{


        when(serviceMock.findeNachId(anyString())).thenReturn(Optional.empty());

        final ResponseEntity<PersonDto> result = restTemplate.exchange("/v1/personen/4711", HttpMethod.GET,null,PersonDto.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

    }

    @Test
    void test6() throws Exception{

        List<Person> personen = List.of(
                Person.builder().id("1").vorname("john").nachname("doe").build(),
                Person.builder().id("2").vorname("john").nachname("rambo").build(),
                Person.builder().id("3").vorname("john").nachname("wick").build()
        );
        when(serviceMock.findeAll()).thenReturn(personen);


        final ResponseEntity<List<PersonDto>> result =
                restTemplate.exchange("/v1/personen", HttpMethod.GET,null,
                        new ParameterizedTypeReference <List<PersonDto>>() {});
        List<PersonDto> resultList = result.getBody();

        assertEquals(3, resultList.size());
        for (PersonDto p: resultList) {
            assertEquals("john", p.getVorname());
        }
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void test7() throws Exception{
        final String uuid = UUID.randomUUID().toString();
        PersonDto validPerson = PersonDto.builder().id(uuid).vorname("john").nachname("doe").build();

        HttpEntity<PersonDto> entity = new HttpEntity<>(validPerson);

        when(serviceMock.speichern(any())).thenReturn(false);
        ResponseEntity<Void> result =restTemplate.exchange("/v1/personen", HttpMethod.PUT,entity,Void.class);


        assertEquals(HttpStatus.CREATED, result.getStatusCode());

    }

    @Test
    void test8() throws Exception{
        final String uuid = UUID.randomUUID().toString();
        Person expectedPerson = Person.builder().id(uuid).vorname("john").nachname("doe").build();
        PersonDto validPerson = PersonDto.builder().id(uuid).vorname("john").nachname("doe").build();

        HttpEntity<PersonDto> entity = new HttpEntity<>(validPerson);

        when(serviceMock.speichern(any())).thenReturn(true);
        ResponseEntity<Void> result =restTemplate.exchange("/v1/personen", HttpMethod.PUT,entity,Void.class);


        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(serviceMock,times(1)).speichern(expectedPerson);

    }

}