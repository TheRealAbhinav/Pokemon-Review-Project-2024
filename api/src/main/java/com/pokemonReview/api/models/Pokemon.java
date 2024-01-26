package com.pokemonReview.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Will add getters, setters, constructors, toString and other stuff
@NoArgsConstructor
@AllArgsConstructor
@Entity // Will let hibernate know that this class is and DB model
@Table(name = "Pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary keys will be incremented by MySQK and not by Hibernate
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
}
