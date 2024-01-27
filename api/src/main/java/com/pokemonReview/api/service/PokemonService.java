package com.pokemonReview.api.service;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.models.Pokemon;

import java.util.List;

public interface PokemonService {
    Pokemon savePokemon(PokemonDto pokemonDto);

    List<Pokemon> getAllPokemons();

    Pokemon findPokemonById(int id);

    Pokemon updatePokemon(PokemonDto pokemonDto, int id);

    Pokemon removePokemonById(int id);
}
