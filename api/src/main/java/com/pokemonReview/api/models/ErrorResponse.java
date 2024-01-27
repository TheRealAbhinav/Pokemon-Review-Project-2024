package com.pokemonReview.api.models;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse {
    private Integer statusCode;
    private String msg;
    private Date timeStamp;
}
