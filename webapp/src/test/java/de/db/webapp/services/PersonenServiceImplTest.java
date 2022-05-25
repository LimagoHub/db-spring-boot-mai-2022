package de.db.webapp.services;

import de.db.webapp.persistence.PersonenRepository;
import de.db.webapp.services.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Mock
    private PersonenRepository repoMock;

    @Mock
    private PersonMapper mapperMock;

    @Mock
    private List<String> antipathenMock;

    @Test
    void speicher_parameterNull_throwsPersonenServiceException() throws Exception {
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null sein!", ex.getMessage());
    }
}