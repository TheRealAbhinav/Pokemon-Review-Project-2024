package com.pokemonReview.api.exceptions;

import com.pokemonReview.api.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePokemonNotFound(PokemonNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMsg(exception.getMessage());
        errorResponse.setTimeStamp(new Date());
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
