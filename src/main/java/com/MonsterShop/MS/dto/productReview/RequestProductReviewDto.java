package com.MonsterShop.MS.dto.productReview;

import com.MonsterShop.MS.entity.Product;
import com.MonsterShop.MS.entity.Review;

public record RequestProductReviewDto(
        Product product,
        Review review
) {
}
