package com.MonsterShop.MS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String username;

    @Column(name = "rating", nullable = false)
    private double rating = 0;

    @NotNull
    private String body;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductReview> productReviews = new ArrayList<>();

    public Review(String username, double rating, String body) {
        this.username = username;
        this.rating = rating;
        this.body = body;
    }

    public Review(Long id, String username, double rating, String body) {
        this.id = id;
        this.username = username;
        this.rating = rating;
        this.body = body;
    }


    public Review() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public double getRating() {
        return rating;
    }

    public String getBody() {
        return body;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
