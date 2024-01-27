package com.pokemonReview.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Reviews:{id = " + id + ", title = " + title + ", content = " + content + ", stars = " + stars;
    }

    @JsonIgnore // We do not want to show this info to user
    @ManyToOne(targetEntity = Pokemon.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "Pokemon_id")
    private Pokemon pokemon;

}
