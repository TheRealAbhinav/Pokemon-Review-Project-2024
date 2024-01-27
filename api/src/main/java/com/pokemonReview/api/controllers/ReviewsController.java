package com.pokemonReview.api.controllers;

import com.pokemonReview.api.dto.ReviewsDto;
import com.pokemonReview.api.models.Reviews;
import com.pokemonReview.api.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewsController {
    private ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    // http://localhost:8080/api/pokemons/1/review - Will create a new review for pokemon with id 1
    @PostMapping("pokemons/{pokemonId}/review")
    public ResponseEntity<Reviews> createReview(@PathVariable int pokemonId, @RequestBody ReviewsDto reviewsDto) {
        return new ResponseEntity<>(reviewsService.createReview(pokemonId, reviewsDto), HttpStatus.CREATED);
    }

    // http://localhost:8080/api/pokemons/1/reviews - Will return all the reviews of pokemon with id 1
    @GetMapping("pokemons/{pokemonId}/reviews")
    public ResponseEntity<List<Reviews>> getPokemonReviewsByPokemonId(@PathVariable int pokemonId) {
        return new ResponseEntity<>(reviewsService.getAllPokemonReviewsByPokemonId(pokemonId), HttpStatus.FOUND);
    }

    // http://localhost:8080/api/pokemons/reviews/1 - Will return the review with id 1
    @GetMapping("pokemons/reviews/{reviewId}")
    public ResponseEntity<Reviews> getReviewById(@PathVariable int reviewId) {
        return new ResponseEntity<>(reviewsService.getReviewById(reviewId), HttpStatus.FOUND);
    }

    // http://localhost:8080/api/pokemons/1/reviews/2 - Will return the review with id 2 of pokemon with id 1
    // Each pokemon can have multiple reviews, using this API we can select individual review of a single pokemon
    @GetMapping("pokemons/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<Reviews> getPokemonReviewByReviewId(@PathVariable int pokemonId, @PathVariable int reviewId) {
        return new ResponseEntity<>(reviewsService.getPokemonReviewByReviewId(pokemonId, reviewId), HttpStatus.FOUND);
    }

    // http://localhost:8080/api/pokemons/1/reviews/2 - Will perform an update on review of id 2 in pokemon with id 1
    @PutMapping("pokemons/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<Reviews> updatePokemonReview(@PathVariable int pokemonId, @PathVariable int reviewId, @RequestBody ReviewsDto reviewsDto) {
        return new ResponseEntity<>(reviewsService.updatePokemonReview(pokemonId, reviewId, reviewsDto), HttpStatus.ACCEPTED);
    }

    // http://localhost:8080/api/pokemons/1/reviews/2 - Will remove review of id 2 in pokemon with id 1
    @DeleteMapping("pokemons/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<String> deletePokemonReviewById(@PathVariable int pokemonId, @PathVariable int reviewId) {
        return ResponseEntity.ok("Review Deleted :: " + reviewsService.deletePokemonReview(pokemonId, reviewId));
    }

}
