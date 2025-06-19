package com.MonsterShop.MS.dto.review;

public record ResponseReviewDto(
        Long id,
        String username,
        double rating,
        String body
) {
}
