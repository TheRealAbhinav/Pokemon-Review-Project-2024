package com.pokemonReview.api.service.impl;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.models.Pokemon;
import com.pokemonReview.api.repository.PokemonRepository;
import com.pokemonReview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository repo;
    @Autowired
    public PokemonServiceImpl(PokemonRepository repo){
        this.repo=repo;
    }
    @Override
    public Pokemon savePokemon(PokemonDto pokemonDto) {
        // We need to convert pokemon DTO to actual pokemon object
        Pokemon pokemonToSave = new Pokemon();
        pokemonToSave.setName(pokemonDto.getName());
        pokemonToSave.setType(pokemonDto.getType());
        // Save the pokemon
        repo.save(pokemonToSave);
        // After the save, MySQl has given an id to the newly saved pokemon, we can show the same to the user.
        return pokemonToSave;
    }
}
