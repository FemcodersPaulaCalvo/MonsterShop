package com.MonsterShop.MS.controller;

import com.MonsterShop.MS.dto.review.RequestReviewDto;
import com.MonsterShop.MS.dto.review.ResponseReviewDto;
import com.MonsterShop.MS.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService REVIEW_SERVICE;

    public ReviewController(ReviewService REVIEW_SERVICE) {
        this.REVIEW_SERVICE = REVIEW_SERVICE;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseReviewDto>> getAllReviewsListByProductId(@PathVariable Long id){
        List<ResponseReviewDto> reviews = REVIEW_SERVICE.getAllReviewsByProductId(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseReviewDto> postNewReviewByProduct(@Valid @RequestBody RequestReviewDto reviewDto){
        ResponseReviewDto newReview = REVIEW_SERVICE.postNewReviewByProductId(reviewDto.productId(), reviewDto);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

}
