package com.pokemonReview.api.controllers;

import com.pokemonReview.api.models.Pokemon;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PokemonController {

    // Use ArrayList instead of List because at Post mapping we are trying to dynamically add an element inside the list
    // Since List are immutable collection, we can not do that at runtime. It will throw an error.
    private ArrayList<Pokemon> pokemons = new ArrayList<>(Arrays.asList(
            new Pokemon(1, "Pikachu", "Electric"),
            new Pokemon(2, "Squirtle", "Water"),
            new Pokemon(3, "Charmander", "Fire"),
            new Pokemon(4, "Balbasaur", "Leaf")
    ));

    // http://localhost:8080/api/pokemons - Will return all pokemons
    @GetMapping("pokemons")
    public ResponseEntity<List<Pokemon>> getPokemons() {
        return ResponseEntity.ok(pokemons);
    }

    // http://localhost:8080/api/pokemon/1 - Will return all pokemon of id 1
    @GetMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable int id) {
        Optional<Pokemon> pokemon = pokemons.stream().filter(P -> P.getId() == id).findFirst();
        return pokemon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
//        if (pokemon.isPresent()){
//            return ResponseEntity.ok(pokemon.get());
//        }
//        else {
//            return ResponseEntity.badRequest().build();
//        }
    }

    // http://localhost:8080/api/pokemon - Will take a Pokemon JSON object and add it to DB and return that pokemon in POJO with its id.
    @PostMapping("pokemon")
    public ResponseEntity<Pokemon> addPokemon(@RequestBody Pokemon newPokemon) {
        newPokemon.setId(pokemons.size() + 1); // default id would be 0, change that.
        pokemons.add(newPokemon); // Add the pokemon to DB
        return new ResponseEntity<>(pokemons.get(pokemons.size() - 1), HttpStatus.CREATED); // Will return an HTTP code of 201 - Created
    }

}
