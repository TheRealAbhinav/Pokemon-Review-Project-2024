package com.pokemonReview.api.service.impl;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.exceptions.PokemonNotFoundException;
import com.pokemonReview.api.models.Pokemon;
import com.pokemonReview.api.repository.PokemonRepository;
import com.pokemonReview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository repo;

    @Autowired
    public PokemonServiceImpl(PokemonRepository repo) {
        this.repo = repo;
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

    @Override
    public List<Pokemon> getAllPokemons() {
        return repo.findAll();
    }

    @Override
    public Pokemon findPokemonById(int id) {
        return repo.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found by ID ::" + id));
    }

    @Override
    public Pokemon updatePokemon(PokemonDto pokemonDto, int id) {
        // Get the pokemon from DB
        Pokemon pokemonToUpdate = repo.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found by ID ::" + id + " - Update Failed"));
        // Perform the updates - Don't change the id
        pokemonToUpdate.setName(pokemonDto.getName());
        pokemonToUpdate.setType(pokemonDto.getType());
        // Save the updated pokemon to DB
        return repo.save(pokemonToUpdate);
    }

    @Override
    public Pokemon removePokemonById(int id) {
        // Get the pokemon from DB
        Pokemon pokemonToRemove = repo.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found by ID ::" + id + " - Delete Failed"));
        // Perform delete
        repo.delete(pokemonToRemove);
        // Return the deleted pokemon
        return pokemonToRemove;
    }
}
