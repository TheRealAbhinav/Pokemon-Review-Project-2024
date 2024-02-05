package com.pokemonReview.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewsDto {
    private String title;
    private String content;
    private int stars;
}
