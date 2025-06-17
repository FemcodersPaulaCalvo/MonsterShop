package com.MonsterShop.MS.dto.product;

import com.MonsterShop.MS.dto.review.ResponseReviewDto;

import java.util.List;

public record ResponseProductDto(
        Long id,
        String name,
        double price,
        String imageUrl,
        double rating,
        int reviewCount,
        boolean featured,
        List<ResponseReviewDto> reviews
) {
}
