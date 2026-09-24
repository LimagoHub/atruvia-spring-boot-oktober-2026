package de.atruvia.webapp.service.internal;

import de.atruvia.webapp.persistence.PersonenRepository;
import de.atruvia.webapp.service.exception.PersonenServiceException;
import de.atruvia.webapp.service.mapper.PersonMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {
    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Mock
    private PersonenRepository personRepositoryMock;

    @Mock
    private PersonMapper mapperMock;


    @DisplayName("Given a null parameter wenn speichen called it should throw PersonenServiceException")
    @Test
    void speichern_paramaterIsNull_throwsPersonenServiceException() {
        final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null sein", ex.getMessage());
    }
}