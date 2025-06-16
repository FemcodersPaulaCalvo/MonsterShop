package com.MonsterShop.MS.dto.review;

import com.MonsterShop.MS.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class MapperReviewDto {
    public static Review toEntity(RequestReviewDto reviewDto){
        return new Review(reviewDto.id(),reviewDto.username(), reviewDto.rating(),reviewDto.body());
    }

    public static ResponseReviewDto fromEntity(Review review){
        return new ResponseReviewDto(review.getId(), review.getUsername(), review.getRating(), review.getBody());
    }
}
