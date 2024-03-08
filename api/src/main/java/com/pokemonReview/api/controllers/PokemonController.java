package com.pokemonReview.api.controllers;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.models.Pokemon;
import com.pokemonReview.api.models.PokemonPageResponse;
import com.pokemonReview.api.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Pokemon", description = "Pokemon CRUD service APIs") // To edit the Swagger API heading
public class PokemonController {
    private PokemonService service;

    @Autowired
    public PokemonController(PokemonService service) {
        this.service = service;
    }

    // http://localhost:8080/api/pokemons - Will return all pokemons
    // Swagger - To give header for individual API
    @Operation(summary = "Fetch all pokemons", description = "Fetch all pokemons present in the DB")
    // Swagger - To give better message for each response from the API
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Pokemons fetched successfully!"),
            @ApiResponse(responseCode = "404", description = "No pokemon present in the DB")}
    )
    @GetMapping("pokemons")
    public ResponseEntity<List<Pokemon>> getPokemons() {
        return ResponseEntity.ok(service.getAllPokemons());
    }

    // http://localhost:8080/api/pokemons/page?pageNo=0&pageSize=5 - Will return all pokemons in Pagination format. each page will show 5 records, and we are requesting first page
    @Operation(summary = "Fetch all pokemons", description = "Fetch all pokemons present in the DB, And return the result in pagination protocol defined at parameters")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pokemons fetched successfully!"), @ApiResponse(responseCode = "404", description = "No pokemon present in the DB"),})
    @GetMapping("pokemons/page")
    public ResponseEntity<PokemonPageResponse> getPokemons(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo, @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(service.getAllPokemons(pageNo, pageSize));
    }

    // http://localhost:8080/api/pokemon/1 - Will return pokemon of id 1
    @Operation(summary = "Fetch a single pokemon", description = "Fetch the pokemon from the DB using ID given in the URL")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pokemon fetched successfully!"), @ApiResponse(responseCode = "404", description = "Pokemon not found, Try with different ID"),})
    @GetMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable int id) {
        return new ResponseEntity<>(service.findPokemonById(id), HttpStatus.OK);
    }

    // http://localhost:8080/api/pokemon - Will take a Pokemon JSON object and add it to DB and return that pokemon in POJO with its id.
    @Operation(summary = "Save pokemon", description = "Save a single pokemon in the DB")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Pokemon saved successfully!"), @ApiResponse(responseCode = "404", description = "Pokemon was not saved. Check JSON."),})
    @PostMapping("pokemon")
    public ResponseEntity<Pokemon> addPokemon(@RequestBody PokemonDto newPokemonDto) {
        return new ResponseEntity<>(service.savePokemon(newPokemonDto), HttpStatus.CREATED);
    }

    // http://localhost:8080/api/pokemon/1 - Will update the pokemon with id 1 with the given update
    @Operation(summary = "Update pokemon", description = "Update a single pokemon with ID given at URL path")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Pokemon updated successfully!"), @ApiResponse(responseCode = "404", description = "Pokemon update failed"),})
    @PutMapping("pokemon/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody PokemonDto updateDto, @PathVariable int id) {
        return new ResponseEntity<>(service.updatePokemon(updateDto, id), HttpStatus.CREATED);
    }

    // http://localhost:8080/api/pokemon/1 - Will remove the pokemon with id 1
    @Operation(summary = "Delete pokemon", description = "Remove a single pokemon from DB with ID given at URL path")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "pokemon deleted successfully!"), @ApiResponse(responseCode = "404", description = "Pokemon was not deleted from DB"),})
    @DeleteMapping("pokemon/{id}")
    public ResponseEntity<String> removePokemon(@PathVariable int id) {
        return ResponseEntity.ok("Deleted Pokemon :: " + service.removePokemonById(id));
    }

}
