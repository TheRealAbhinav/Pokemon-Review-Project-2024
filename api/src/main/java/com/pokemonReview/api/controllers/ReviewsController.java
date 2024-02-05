package com.pokemonReview.api.controllers;

import com.pokemonReview.api.dto.ReviewsDto;
import com.pokemonReview.api.models.Reviews;
import com.pokemonReview.api.service.ReviewsService;
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
@Tag(name = "Reviews", description = "Pokemon Reviews CRUD service APIs")
public class ReviewsController {
    private ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    // http://localhost:8080/api/pokemons/1/review - Will create a new review for pokemon with id 1
    @Operation(summary = "Create review", description = "Create a single new review for pokemon with ID given at URL path")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Review added for the pokemon successfully!"), @ApiResponse(responseCode = "404", description = "Review was not created"),})
    @PostMapping("pokemons/{pokemonId}/review")
    public ResponseEntity<Reviews> createReview(@PathVariable int pokemonId, @RequestBody ReviewsDto reviewsDto) {
        return new ResponseEntity<>(reviewsService.createReview(pokemonId, reviewsDto), HttpStatus.CREATED);
    }

    // http://localhost:8080/api/pokemons/1/reviews - Will return all the reviews of pokemon with id 1
    @Operation(summary = "Fetch review", description = "Fetch a single or multiple reviews for pokemon with ID present at the URL path")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All the reviews fetched for the pokemon successfully!"), @ApiResponse(responseCode = "404", description = "Reviews not present"),})
    @GetMapping("pokemons/{pokemonId}/reviews")
    public ResponseEntity<List<Reviews>> getPokemonReviewsByPokemonId(@PathVariable int pokemonId) {
        return new ResponseEntity<>(reviewsService.getAllPokemonReviewsByPokemonId(pokemonId), HttpStatus.OK);
    }

    // http://localhost:8080/api/pokemons/reviews/1 - Will return the review with id 1
    @Operation(summary = "Fetch single review", description = "Fetch a single review from the DB with ID present at the URL path")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Review added for the pokemon successfully!"), @ApiResponse(responseCode = "404", description = "Review was not created"),})
    @GetMapping("pokemons/reviews/{reviewId}")
    public ResponseEntity<Reviews> getReviewById(@PathVariable int reviewId) {
        return new ResponseEntity<>(reviewsService.getReviewById(reviewId), HttpStatus.OK);
    }

    // http://localhost:8080/api/pokemons/1/reviews/2 - Will return the review with id 2 of pokemon with id 1
    // Each pokemon can have multiple reviews, using this API we can select individual review of a single pokemon
    @Operation(summary = "Fetch single review for a pokemon", description = "Will fetch a single review for a single pokemon")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Review fetched successfully!"), @ApiResponse(responseCode = "404", description = "Review was not fetched"),})
    @GetMapping("pokemons/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<Reviews> getPokemonReviewByReviewId(@PathVariable int pokemonId, @PathVariable int reviewId) {
        return new ResponseEntity<>(reviewsService.getPokemonReviewByReviewId(pokemonId, reviewId), HttpStatus.OK);
    }

    // http://localhost:8080/api/pokemons/1/reviews/2 - Will perform an update on review of id 2 in pokemon with id 1
    @Operation(summary = "Update a single review for a pokemon", description = "Will update a single review for a single pokemon")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Review updated successfully!"), @ApiResponse(responseCode = "404", description = "Review was not updated"),})
    @PutMapping("pokemons/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<Reviews> updatePokemonReview(@PathVariable int pokemonId, @PathVariable int reviewId, @RequestBody ReviewsDto reviewsDto) {
        return new ResponseEntity<>(reviewsService.updatePokemonReview(pokemonId, reviewId, reviewsDto), HttpStatus.ACCEPTED);
    }

    // http://localhost:8080/api/pokemons/1/reviews/2 - Will remove review of id 2 in pokemon with id 1
    @Operation(summary = "Delete a single review for a pokemon", description = "Will delete a single review for a single pokemon")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Review deleted successfully!"), @ApiResponse(responseCode = "404", description = "Review was not removed"),})
    @DeleteMapping("pokemons/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<String> deletePokemonReviewById(@PathVariable int pokemonId, @PathVariable int reviewId) {
        return ResponseEntity.ok("Review Deleted :: " + reviewsService.deletePokemonReview(pokemonId, reviewId));
    }

}
