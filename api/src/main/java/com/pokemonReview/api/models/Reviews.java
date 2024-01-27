package com.pokemonReview.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Will let hibernate know that this class is and DB model
@Table(name = "Reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "stars")
    private int stars;

    @ManyToOne(targetEntity = Pokemon.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "Pokemon_id")
    private Pokemon pokemon;

}
