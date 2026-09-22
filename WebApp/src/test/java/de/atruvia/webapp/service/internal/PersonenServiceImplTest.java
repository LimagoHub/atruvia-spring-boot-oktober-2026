package de.atruvia.webapp.service.internal;

import de.atruvia.webapp.persistence.PersonenRepository;
import de.atruvia.webapp.service.mapper.PersonMapper;
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

    @Test
    void xxx() {

    }
}