package com.MonsterShop.MS.repository;

import com.MonsterShop.MS.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    List<ProductReview> findAllByProductId(Long id);
}
