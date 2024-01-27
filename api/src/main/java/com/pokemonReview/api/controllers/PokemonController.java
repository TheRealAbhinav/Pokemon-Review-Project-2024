package com.pokemonReview.api.controllers;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.models.Pokemon;
import com.pokemonReview.api.models.PokemonPageResponse;
import com.pokemonReview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // http://localhost:8080/api/pokemons/page?pageNo=0&pageSize=5 - Will return all pokemons in Pagination format. each page will show 5 records, and we are requesting first page
    @GetMapping("pokemons/page")
    public ResponseEntity<PokemonPageResponse> getPokemons(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(service.getAllPokemons(pageNo,pageSize));
    }

    // http://localhost:8080/api/pokemon/1 - Will return pokemon of id 1
    @GetMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable int id) {
        return new ResponseEntity<>(service.findPokemonById(id), HttpStatus.FOUND);
    }

    // http://localhost:8080/api/pokemon - Will take a Pokemon JSON object and add it to DB and return that pokemon in POJO with its id.
    @PostMapping("pokemon")
    public ResponseEntity<Pokemon> addPokemon(@RequestBody PokemonDto newPokemonDto) {
        return new ResponseEntity<>(service.savePokemon(newPokemonDto), HttpStatus.CREATED);
    }

    // http://localhost:8080/api/pokemon/1 - Will update the pokemon with id 1 with the given update
    @PutMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody PokemonDto updateDto, @PathVariable int id) {
        return new ResponseEntity<>(service.updatePokemon(updateDto, id), HttpStatus.ACCEPTED);
    }

    // http://localhost:8080/api/pokemon/1 - Will remove the pokemon with id 1
    @DeleteMapping("pokemon/{id}")
    public ResponseEntity<String> removePokemon(@PathVariable int id) {
        return ResponseEntity.ok("Deleted Pokemon :: " + service.removePokemonById(id));
    }

}
