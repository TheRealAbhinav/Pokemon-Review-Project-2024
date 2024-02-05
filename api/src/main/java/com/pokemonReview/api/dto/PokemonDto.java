package com.pokemonReview.api.dto;

import lombok.Data;

/*
    DTO - Data Transfer object
    - it is mainly used to transfer data between controller and Service layer
    - It gives us the flexibility to not use model class at the controller
    - Suppose an API does not require to pass id field of pokemon, Using model class this is not possible but we can achieve this using DTO
 */
@Data
public class PokemonDto {
    private String name;
    private String type;
}
