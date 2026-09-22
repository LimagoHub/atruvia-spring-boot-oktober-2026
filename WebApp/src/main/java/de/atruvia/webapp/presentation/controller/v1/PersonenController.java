package de.atruvia.webapp.presentation.controller.v1;

import de.atruvia.webapp.presentation.dto.PersonDto;
import de.atruvia.webapp.presentation.error.IdMismatchException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
public class PersonenController {

    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDto.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})



    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<PersonDto> findPersonById(@PathVariable  UUID id) {

        if(id.toString().endsWith("1"))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(PersonDto.builder()
                .id(id)
                .vorname("John")
                .nachname("Doe")
                .build());
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<Iterable<PersonDto>> findAll(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Schmitt") String nachname
    ) {

        System.out.println("vorname: " + vorname + ",  nachname: " + nachname);
        var list = List.of(
                PersonDto.builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Doe")
                        .build(),
        PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("Wayne")
                .build(),
        PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("Rambo")
                .build(),
        PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("Wick")
                .build(),
        PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("McClain")
                .build(),
        PersonDto.builder()
                .id(UUID.randomUUID())
                .vorname("John Boy")
                .nachname("Walton")
                .build()


        );
        return ResponseEntity.ok(list);

    }

    @DeleteMapping(path = "/{id}" )
    public ResponseEntity<Void> deletePerson(@PathVariable  UUID id) {
        if(id.toString().endsWith("1"))
            return ResponseEntity.notFound().build();

        System.out.println("Person wurde geloescht");
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> einfuegen(@Valid @RequestBody PersonDto personDto, UriComponentsBuilder uriBuilder) {

        System.out.println("Person wurde gespeichert");
        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id,@Valid @RequestBody PersonDto personDto){
        if (! id.equals(personDto.getId())) throw new IdMismatchException("ID mismatch");
        System.out.println("Person geaendert");
        return ResponseEntity.ok().build();
    }

}
