package com.pokemonReview.api.service;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.models.Pokemon;
import com.pokemonReview.api.models.PokemonPageResponse;

import java.util.List;

public interface PokemonService {
    Pokemon savePokemon(PokemonDto pokemonDto);

    List<Pokemon> getAllPokemons();
    PokemonPageResponse getAllPokemons(int pageNumber, int pageSize);

    Pokemon findPokemonById(int id);

    Pokemon updatePokemon(PokemonDto pokemonDto, int id);

    Pokemon removePokemonById(int id);
}
