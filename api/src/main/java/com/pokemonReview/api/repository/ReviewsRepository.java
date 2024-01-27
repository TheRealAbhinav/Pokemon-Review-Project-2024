package com.pokemonReview.api.repository;

import com.pokemonReview.api.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
    List<Reviews> findByPokemonId(int id);
}
