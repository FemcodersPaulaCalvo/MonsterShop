package com.MonsterShop.MS.dto.product;

public record ResponseProductDto(
        Long id,
        String name,
        double price,
        String imageUrl,
        double rating,
        int reviewCount,
        boolean featured
) {
}
