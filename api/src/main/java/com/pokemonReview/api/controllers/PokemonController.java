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
    private ArrayList<Pokemon> pokemons = new ArrayList<>(Arrays.asList(new Pokemon(1, "Pikachu", "Electric"), new Pokemon(2, "Squirtle", "Water"), new Pokemon(3, "Charmander", "Fire"), new Pokemon(4, "Balbasaur", "Leaf")));

    // http://localhost:8080/api/pokemons - Will return all pokemons
    @GetMapping("pokemons")
    public ResponseEntity<List<Pokemon>> getPokemons() {
        // Retrun all pokemons in the DB
        return ResponseEntity.ok(pokemons);
    }

    // http://localhost:8080/api/pokemon/1 - Will return pokemon of id 1
    @GetMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable int id) {
        // Find the pokemon
        Optional<Pokemon> pokemon = pokemons.stream().filter(P -> P.getId() == id).findFirst();
        if (pokemon.isPresent()) {
            // Return the pokemon
            return ResponseEntity.ok(pokemon.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // http://localhost:8080/api/pokemon - Will take a Pokemon JSON object and add it to DB and return that pokemon in POJO with its id.
    @PostMapping("pokemon")
    public ResponseEntity<Pokemon> addPokemon(@RequestBody Pokemon newPokemon) {
        // Default id would be 0, change that.
        newPokemon.setId(pokemons.size() + 1);
        // Add the pokemon to DB
        pokemons.add(newPokemon);
        return new ResponseEntity<>(pokemons.get(pokemons.size() - 1), HttpStatus.CREATED); // Will return an HTTP code of 201 - Created
    }

    // http://localhost:8080/api/pokemon/1 - Will update the pokemon with id 1 with the given update
    @PutMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon update, @PathVariable int id) {
        if (update.getId() == 0) {
            // If user has not defined any id, set the ID passed in the path variable
            // This will prepare a new updated pokemon of ID same as old pokemon
            update.setId(id);
        }
        // Find the pokemon to update
        Optional<Pokemon> pokemonToUpdate = pokemons.stream().filter(P -> P.getId() == id).findFirst();
        if (pokemonToUpdate.isPresent()) {
            // remove that pokemon
            pokemons.remove(pokemonToUpdate.get());
            // Add the updated pokemon back to the DB
            pokemons.add(update);
            return ResponseEntity.ok(pokemons.get(pokemons.size() - 1));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // http://localhost:8080/api/pokemon/1 - Will remove the pokemon with id 1
    @DeleteMapping("pokemon/{id}")
    public ResponseEntity<String> removePokemon(@PathVariable int id) {
        // find the pokemon to delete
        Optional<Pokemon> pokemonToRemove = pokemons.stream().filter(P -> P.getId() == id).findFirst();
        if (pokemonToRemove.isPresent()) {
            // Delete the pokemon
            pokemons.remove(pokemonToRemove.get());
            // Return message
            return ResponseEntity.ok("Pokemon removed :: " + pokemonToRemove.get());
        } else {
            // Return not found message
            return new ResponseEntity<>("Pokemon with id " + id + " - NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

}
