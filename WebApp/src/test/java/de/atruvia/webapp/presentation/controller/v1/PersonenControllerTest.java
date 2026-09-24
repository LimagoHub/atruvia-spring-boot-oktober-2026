package de.atruvia.webapp.presentation.controller.v1;

import de.atruvia.webapp.presentation.dto.PersonDto;
import de.atruvia.webapp.service.PersonenService;
import de.atruvia.webapp.service.exception.PersonenServiceException;
import de.atruvia.webapp.service.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureTestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonenService personenServiceMock;


    @Test
    void test1() throws Exception{

        // Arrange
        final var optionalPerson = Optional.of(new Person(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"),"John","Doe"));
        // Recordmode
        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

        PersonDto personDto = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        assertEquals("John", personDto.getVorname());
    }

    @Test
    void test2() throws Exception{

        // Arrange
        final var optionalPerson = Optional.of(new Person(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"),"John","Doe"));
        // Recordmode
        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

        String personDto = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        System.out.println(personDto);

    }

    @Test
    void test3() throws Exception{

        // Arrange
        final var optionalPerson = Optional.of(new Person(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"),"John","Doe"));
        // Recordmode
        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        PersonDto personDto = entity.getBody();


        // Assertion
        assertEquals("John", personDto.getVorname());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        verify(personenServiceMock, times(1)).findeNachId(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"));
    }

    @Test
    void test4() throws Exception{

        // Arrange
        final Optional<Person> optionalPerson = Optional.empty();
        // Recordmode
        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        PersonDto personDto = entity.getBody();

        //assertEquals("John", personDto.getVorname());
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
        verify(personenServiceMock, times(1)).findeNachId(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"));
    }

    @Test
    void test5() throws Exception{

        // Arrange

        // Recordmode
        when(personenServiceMock.findeNachId(any())).thenThrow(PersonenServiceException.class);

        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        PersonDto personDto = entity.getBody();

        //assertEquals("John", personDto.getVorname());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
        verify(personenServiceMock, times(1)).findeNachId(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"));
    }

    @Test
    void test6() throws Exception{

        PersonDto personToUpload = PersonDto.builder().id(UUID.randomUUID()).vorname("Erika").nachname("Mustermann").build();
        HttpEntity<PersonDto> requestEntity = new HttpEntity<>(personToUpload);

        var personen = List.of(
                new Person(UUID.randomUUID(),"John","Doe"),
                new Person(UUID.randomUUID(),"Jane","Doe"));
        when(personenServiceMock.findeAlle()).thenReturn(personen);
        //doThrow(PersonenServiceException.class).when(personenServiceMock).findeAlle();

        var entity = restTemplate.exchange("/v1/personen", HttpMethod.GET,requestEntity,new ParameterizedTypeReference<List<PersonDto>>() { });
        var liste = entity.getBody();
        assertEquals(2,liste.size());
        verify(personenServiceMock, times(1)).findeAlle();
    }
}