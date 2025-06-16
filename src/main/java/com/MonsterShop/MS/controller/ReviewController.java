package com.MonsterShop.MS.controller;

import com.MonsterShop.MS.dto.review.ResponseReviewDto;
import com.MonsterShop.MS.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
