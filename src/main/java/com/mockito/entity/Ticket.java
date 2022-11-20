package com.mockito.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Desc cannot be blank!")
    private String desc;

    public Ticket(String desc){
        this.desc = desc;
    }


}
