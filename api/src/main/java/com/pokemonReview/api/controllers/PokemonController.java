package com.pokemonReview.api.controllers;

import com.pokemonReview.api.models.Pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PokemonController {

    private List<Pokemon> pokemons = List.of(
            new Pokemon(1,"Pikachu","Electric"),
            new Pokemon(2,"Squirtle","Water"),
            new Pokemon(3,"Charmander","Fire"),
            new Pokemon(4,"Balbasaur","Leaf")
    );

    @GetMapping("pokemons") // http://localhost:8080/api/pokemons - Will return all pokemons
    public ResponseEntity<List<Pokemon>> getPokemons(){
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("pokemon/{id}") // http://localhost:8080/api/pokemon/1 - Will return all pokemon of id 1
    public ResponseEntity<Pokemon> getPokemon(@PathVariable int id){
        Optional<Pokemon> pokemon = pokemons.stream().filter(P -> P.getId() == id).findFirst();
        return pokemon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
//        if (pokemon.isPresent()){
//            return ResponseEntity.ok(pokemon.get());
//        }
//        else {
//            return ResponseEntity.badRequest().build();
//        }
    }

}
