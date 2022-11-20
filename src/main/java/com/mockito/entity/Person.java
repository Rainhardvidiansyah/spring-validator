package com.mockito.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Person {

    @NotBlank(message = "Name must be written")
    @Size(max = 30, message = "Name must be 30 max characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Size(max = 20, message = "email must 20 max characters")
    private String email;
}
