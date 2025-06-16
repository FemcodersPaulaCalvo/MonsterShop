package com.MonsterShop.MS.service;

import com.MonsterShop.MS.dto.review.MapperReviewDto;
import com.MonsterShop.MS.dto.review.RequestReviewDto;
import com.MonsterShop.MS.dto.review.ResponseReviewDto;
import com.MonsterShop.MS.entity.Product;
import com.MonsterShop.MS.entity.ProductReview;
import com.MonsterShop.MS.entity.Review;
import com.MonsterShop.MS.repository.ProductRepository;
import com.MonsterShop.MS.repository.ProductReviewRepository;
import com.MonsterShop.MS.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ProductRepository PRODUCT_REPOSITORY;
    private final ProductService PRODUCT_SERVICE;
    private final ReviewRepository REVIEW_REPOSITORY;
    private final ProductReviewRepository PRODUCT_REVIEW_REPOSITORY;

    public ReviewService(ProductRepository PRODUCT_REPOSITORY, ProductService PRODUCT_SERVICE, ReviewRepository REVIEW_REPOSITORY, ProductReviewRepository PRODUCT_REVIEW_REPOSITORY) {
        this.PRODUCT_REPOSITORY = PRODUCT_REPOSITORY;
        this.PRODUCT_SERVICE = PRODUCT_SERVICE;
        this.REVIEW_REPOSITORY = REVIEW_REPOSITORY;
        this.PRODUCT_REVIEW_REPOSITORY = PRODUCT_REVIEW_REPOSITORY;
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

    //  POST NEW REVIEW BY A PRODUCT
    public ResponseReviewDto postNewReviewByProductId(Long productId, RequestReviewDto reviewDto){
        Product isExistingProduct = PRODUCT_REPOSITORY.findById(productId)
                .orElseThrow(() -> new RuntimeException("Id not found"));
        Review newReview = MapperReviewDto.toEntity(reviewDto);
        Review review = REVIEW_REPOSITORY.save(newReview);

        ProductReview newProductreview = new ProductReview(isExistingProduct, newReview);

        PRODUCT_REVIEW_REPOSITORY.save(newProductreview);
        PRODUCT_SERVICE.updateProductReviewStats(isExistingProduct);

        return MapperReviewDto.fromEntity(review);

    }
}
