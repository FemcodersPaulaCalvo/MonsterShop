package com.MonsterShop.MS.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RequestProductDto(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must contain min 2 and max 50 characters")
        String name,

        @Positive(message = "The number entered must be positive")
        double price,

        @NotBlank(message = "The url image is required")
        @Size(min = 2, message = "Name must contain min 2")
        String imageUrl,

        boolean featured
) {
}
