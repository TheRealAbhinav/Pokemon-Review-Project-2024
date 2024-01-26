package com.pokemonReview.api.controllers;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.models.Pokemon;
import com.pokemonReview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PokemonService service;

    @Autowired
    public PokemonController(PokemonService service) {
        this.service = service;
    }

    // http://localhost:8080/api/pokemons - Will return all pokemons
    @GetMapping("pokemons")
    public ResponseEntity<List<Pokemon>> getPokemons() {
        return ResponseEntity.ok(service.getAllPokemons());
    }

    // http://localhost:8080/api/pokemon/1 - Will return pokemon of id 1
    @GetMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable int id) {
        Optional<Pokemon> pokemon = service.findPokemonById(id);
        if (pokemon.isPresent()) {
            return ResponseEntity.ok(pokemon.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // http://localhost:8080/api/pokemon - Will take a Pokemon JSON object and add it to DB and return that pokemon in POJO with its id.
    @PostMapping("pokemon")
    public ResponseEntity<Pokemon> addPokemon(@RequestBody PokemonDto newPokemonDto) {
        Pokemon savedPokemon = service.savePokemon(newPokemonDto);
        return ResponseEntity.ok(savedPokemon);
    }

//    // http://localhost:8080/api/pokemon/1 - Will update the pokemon with id 1 with the given update
//    @PutMapping("pokemon/{id}")
//    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon update, @PathVariable int id) {
//        if (update.getId() == 0) {
//            // If user has not defined any id, set the ID passed in the path variable
//            // This will prepare a new updated pokemon of ID same as old pokemon
//            update.setId(id);
//        }
//        // Find the pokemon to update
//        Optional<Pokemon> pokemonToUpdate = pokemons.stream().filter(P -> P.getId() == id).findFirst();
//        if (pokemonToUpdate.isPresent()) {
//            // remove that pokemon
//            pokemons.remove(pokemonToUpdate.get());
//            // Add the updated pokemon back to the DB
//            pokemons.add(update);
//            return ResponseEntity.ok(pokemons.get(pokemons.size() - 1));
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    // http://localhost:8080/api/pokemon/1 - Will remove the pokemon with id 1
//    @DeleteMapping("pokemon/{id}")
//    public ResponseEntity<String> removePokemon(@PathVariable int id) {
//        // find the pokemon to delete
//        Optional<Pokemon> pokemonToRemove = pokemons.stream().filter(P -> P.getId() == id).findFirst();
//        if (pokemonToRemove.isPresent()) {
//            // Delete the pokemon
//            pokemons.remove(pokemonToRemove.get());
//            // Return message
//            return ResponseEntity.ok("Pokemon removed :: " + pokemonToRemove.get());
//        } else {
//            // Return not found message
//            return new ResponseEntity<>("Pokemon with id " + id + " - NOT FOUND", HttpStatus.NOT_FOUND);
//        }
//    }

}
