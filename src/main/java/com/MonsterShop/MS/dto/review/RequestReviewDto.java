package com.MonsterShop.MS.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record RequestReviewDto(
        Long id,

        @NotBlank(message = "Username is required")
        @Size(min = 2, max = 50, message = "Username must contain min 2 and max 50 characters")
        String username,

        @PositiveOrZero(message = "The number entered must be positive or zero")
        @Max(5)
        double rating,

        @NotBlank(message = "The body is required")
        @Size(min = 3, message = "Name must contain min 3")
        String body
) {
}
