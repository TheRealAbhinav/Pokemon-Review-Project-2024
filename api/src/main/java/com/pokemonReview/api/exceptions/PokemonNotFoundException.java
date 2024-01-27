package com.pokemonReview.api.exceptions;

public class PokemonNotFoundException extends RuntimeException {
    private static final long serialVerionUID = 1;

    public PokemonNotFoundException(String msg) {
        super(msg);
    }
}
