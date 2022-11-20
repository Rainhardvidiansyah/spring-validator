package com.mockito.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
public class Person {

    @NotBlank(message = "Name must be written")
    @Size(max = 10, message = "Name must be 30 max characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Size(max = 10, message = "email must 20 max characters")
    private String email;

    @NotNull(message = "Ticket cannot be null!")
    @Valid
    private Ticket ticket;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
