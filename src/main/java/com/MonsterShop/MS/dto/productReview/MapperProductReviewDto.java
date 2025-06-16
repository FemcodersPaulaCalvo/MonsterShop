package com.MonsterShop.MS.dto.productReview;

import com.MonsterShop.MS.entity.ProductReview;
import org.springframework.stereotype.Component;

@Component
public class MapperProductReviewDto {
    public static ProductReview toEntity(RequestProductReviewDto productReviewDto){
        return new ProductReview(productReviewDto.id_product(), productReviewDto.id_review());
    }

    public static ResponseProductReviewDto fromEntity(ProductReview productReview){
        return new ResponseProductReviewDto(productReview.getId(), productReview.getProduct(), productReview.getReview());
    }
}
