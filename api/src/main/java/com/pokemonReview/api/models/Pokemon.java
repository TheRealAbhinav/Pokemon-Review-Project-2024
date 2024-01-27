package com.pokemonReview.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data // Will add getters, setters, constructors, toString and other stuff
@NoArgsConstructor
@AllArgsConstructor
@Entity // Will let hibernate know that this class is and DB model
@Table(name = "Pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary keys will be incremented by MySQL and not by Hibernate
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Reviews> reviews = new ArrayList<>();
}
