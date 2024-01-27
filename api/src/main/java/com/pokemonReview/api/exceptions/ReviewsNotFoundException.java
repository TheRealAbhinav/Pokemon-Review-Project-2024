package com.pokemonReview.api.exceptions;

public class ReviewsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2;

    public ReviewsNotFoundException(String msg) {
        super(msg);
    }
}
