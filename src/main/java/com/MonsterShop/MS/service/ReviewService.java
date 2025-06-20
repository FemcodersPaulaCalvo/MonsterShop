package com.MonsterShop.MS.service;

import com.MonsterShop.MS.dto.product.MapperProductDto;
import com.MonsterShop.MS.dto.review.MapperReviewDto;
import com.MonsterShop.MS.dto.review.RequestReviewDto;
import com.MonsterShop.MS.dto.review.ResponseReviewDto;
import com.MonsterShop.MS.entity.Product;
import com.MonsterShop.MS.entity.ProductReview;
import com.MonsterShop.MS.entity.Review;
import com.MonsterShop.MS.exceptions.EmptyListException;
import com.MonsterShop.MS.exceptions.NoIdProductFoundException;
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
        Product isExistingProduct = getIsExistingProduct(productId);
        return getReviewDtoList(isExistingProduct);
    }


    //  POST NEW REVIEW BY A PRODUCT
    public ResponseReviewDto postNewReviewByProductId(Long productId, RequestReviewDto reviewDto){
        Product isExistingProduct = getIsExistingProduct(productId);
        Review newReview = MapperReviewDto.toEntity(reviewDto);
        Review newReviewNoId = new Review(newReview.getUsername(),newReview.getRating(),newReview.getBody());

        Review review = REVIEW_REPOSITORY.save(newReviewNoId);

        ProductReview newProductReview = new ProductReview(isExistingProduct, newReviewNoId);

        PRODUCT_REVIEW_REPOSITORY.save(newProductReview);
        PRODUCT_SERVICE.updateProductReviewStats(productId);

        return MapperReviewDto.fromEntity(review);

    }


    //  HELPER METHODS
    private Product getIsExistingProduct(Long productId) {
        Product isExistingProduct = PRODUCT_REPOSITORY.findById(productId)
                .orElseThrow(() -> new NoIdProductFoundException(productId));
        return isExistingProduct;
    }

    private static List<ResponseReviewDto> getReviewDtoList(Product isExistingProduct) {
        return isExistingProduct.getProductReviews()
                .stream()
                .map(ProductReview::getReview)
                .map(review -> {
                    if (review == null) {
                        throw new EmptyListException();
                    }
                    return MapperReviewDto.fromEntity(review);
                })
                .toList();
    }

}
