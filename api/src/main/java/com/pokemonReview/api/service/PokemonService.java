package com.pokemonReview.api.service;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.models.Pokemon;

public interface PokemonService {
    Pokemon savePokemon(PokemonDto pokemonDto);
}
