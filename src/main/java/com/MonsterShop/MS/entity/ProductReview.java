package com.MonsterShop.MS.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_review")
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_review")
    private Review review;

    public ProductReview() {
    }

    public ProductReview(Product product, Review review) {
        this.product = product;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Review getReview() {
        return review;
    }
}
