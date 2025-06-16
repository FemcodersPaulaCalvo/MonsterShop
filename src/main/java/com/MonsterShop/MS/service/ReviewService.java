package com.MonsterShop.MS.service;

import com.MonsterShop.MS.dto.review.MapperReviewDto;
import com.MonsterShop.MS.dto.review.ResponseReviewDto;
import com.MonsterShop.MS.entity.Product;
import com.MonsterShop.MS.entity.ProductReview;
import com.MonsterShop.MS.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ProductRepository PRODUCT_REPOSITORY;
    private final ReviewService REVIEW_SERVICE;

    public ReviewService(ProductRepository PRODUCT_REPOSITORY, ReviewService REVIEW_SERVICE) {
        this.PRODUCT_REPOSITORY = PRODUCT_REPOSITORY;
        this.REVIEW_SERVICE = REVIEW_SERVICE;
    }

    //  GET REVIEWS BY A PRODUCT ID
    public List<ResponseReviewDto> getAllReviewsByProductId(Long productId){
        Product isExistingProduct = PRODUCT_REPOSITORY.findById(productId)
                .orElseThrow(() -> new RuntimeException("Id not found"));

        return isExistingProduct.getProductReviews()
                .stream()
                .map(ProductReview::getReview)
                .map(review -> MapperReviewDto.fromEntity(review))
                .toList();
    }
}
