package com.pokemonReview.api.controllers;

import com.pokemonReview.api.models.Pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonController {

    @GetMapping("pokemons") // http://localhost:8080/api/pokemons - Will return all pokemons
    public ResponseEntity<List<Pokemon>> getPokemons(){
        List<Pokemon> pokemons = List.of(
                new Pokemon(1,"Pikachu","Electric"),
                new Pokemon(2,"Squirtle","Water"),
                new Pokemon(3,"Charmander","Fire"),
                new Pokemon(4,"Balbasaur","Leaf")
        );
        return ResponseEntity.ok(pokemons);
    }

}
