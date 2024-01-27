package com.pokemonReview.api.service.impl;

import com.pokemonReview.api.dto.ReviewsDto;
import com.pokemonReview.api.exceptions.PokemonNotFoundException;
import com.pokemonReview.api.exceptions.ReviewsNotFoundException;
import com.pokemonReview.api.models.Pokemon;
import com.pokemonReview.api.models.Reviews;
import com.pokemonReview.api.repository.PokemonRepository;
import com.pokemonReview.api.repository.ReviewsRepository;
import com.pokemonReview.api.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    private ReviewsRepository reviewsRepo;
    private PokemonRepository pokemonRepo;

    @Autowired
    public ReviewsServiceImpl(ReviewsRepository reviewsRepo, PokemonRepository pokemonRepo) {
        this.reviewsRepo = reviewsRepo;
        this.pokemonRepo = pokemonRepo;
    }

    @Override
    public Reviews createReview(int pokemonId, ReviewsDto reviewsDto) {
        Pokemon pokemon = pokemonRepo.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found by ID :: " + pokemonId));
        Reviews reviews = new Reviews();
        reviews.setPokemon(pokemon);
        reviews.setContent(reviewsDto.getContent());
        reviews.setTitle(reviewsDto.getTitle());
        reviews.setStars(reviewsDto.getStars());
        return reviewsRepo.save(reviews);
    }

    @Override
    public List<Reviews> getAllPokemonReviewsByPokemonId(int pokemonId) {
        return reviewsRepo.findByPokemonId(pokemonId);
    }

    @Override
    public Reviews getReviewById(int reviewsId) {
        return reviewsRepo.findById(reviewsId).orElseThrow(() -> new ReviewsNotFoundException("Review not found by ID :: " + reviewsId));
    }

    @Override
    public Reviews getPokemonReviewByReviewId(int pokemonId, int reviewId) {
        Pokemon pokemon = pokemonRepo.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found by ID :: " + pokemonId));
        Reviews review = reviewsRepo.findById(reviewId).orElseThrow(() -> new ReviewsNotFoundException("Review not found by ID :: " + reviewId));
        if (pokemon.getId() != review.getPokemon().getId()) {
            throw new ReviewsNotFoundException("Review id :: " + reviewId + " does not match with pokemon id :: " + pokemonId);
        }
        return review;
    }

    @Override
    public Reviews updatePokemonReview(int pokemonId, int reviewId, ReviewsDto reviewsDto) {
        Pokemon pokemon = pokemonRepo.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found by ID :: " + pokemonId));
        Reviews review = reviewsRepo.findById(reviewId).orElseThrow(() -> new ReviewsNotFoundException("Review not found by ID :: " + reviewId));
        if (pokemon.getId() != review.getPokemon().getId()) {
            throw new ReviewsNotFoundException("Review id :: " + reviewId + " does not match with pokemon id :: " + pokemonId);
        }
        review.setPokemon(pokemon);
        review.setTitle(reviewsDto.getTitle());
        review.setStars(reviewsDto.getStars());
        review.setContent(reviewsDto.getContent());
        return reviewsRepo.save(review);
    }

    @Override
    public Reviews deletePokemonReview(int pokemonId, int reviewId) {
        Pokemon pokemon = pokemonRepo.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found by ID :: " + pokemonId));
        Reviews review = reviewsRepo.findById(reviewId).orElseThrow(() -> new ReviewsNotFoundException("Review not found by ID :: " + reviewId));
        if (pokemon.getId() != review.getPokemon().getId()) {
            throw new ReviewsNotFoundException("Review id :: " + reviewId + " does not match with pokemon id :: " + pokemonId);
        }
        reviewsRepo.delete(review);
        return review;
    }
}
