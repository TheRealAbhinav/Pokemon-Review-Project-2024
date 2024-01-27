package com.pokemonReview.api.service;

import com.pokemonReview.api.dto.ReviewsDto;
import com.pokemonReview.api.models.Reviews;

import java.util.List;

public interface ReviewsService {
    Reviews createReview(int pokemonId, ReviewsDto reviewsDto);

    List<Reviews> getAllPokemonReviewsByPokemonId(int pokemonId);

    Reviews getReviewById(int reviewsId);

    Reviews getPokemonReviewByReviewId(int pokemonId, int reviewId);

    Reviews updatePokemonReview(int pokemonId, int reviewId, ReviewsDto reviewsDto);

    Reviews deletePokemonReview(int pokemonId, int reviewId);
}
