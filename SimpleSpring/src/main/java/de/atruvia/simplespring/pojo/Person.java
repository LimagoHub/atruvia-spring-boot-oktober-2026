package de.atruvia.simplespring.pojo;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private UUID id;
    private String firstName;
    private String lastName;


}
