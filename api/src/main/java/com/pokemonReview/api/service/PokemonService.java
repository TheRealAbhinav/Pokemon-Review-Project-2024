package com.pokemonReview.api.service;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.models.Pokemon;

import java.util.List;
import java.util.Optional;

public interface PokemonService {
    Pokemon savePokemon(PokemonDto pokemonDto);
    List<Pokemon> getAllPokemons();
    Optional<Pokemon> findPokemonById(int id);
}
