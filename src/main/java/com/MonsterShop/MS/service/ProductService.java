package com.MonsterShop.MS.service;

import com.MonsterShop.MS.dto.product.MapperProductDto;
import com.MonsterShop.MS.dto.product.ResponseProductDto;
import com.MonsterShop.MS.entity.Product;
import com.MonsterShop.MS.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository PRODUCT_REPOSITORY;
//    private final ReviewService REVIEW_SERVICE;

    public ProductService(ProductRepository PRODUCT_REPOSITORY) {
        this.PRODUCT_REPOSITORY = PRODUCT_REPOSITORY;
    }

    //  GET ALL PRODUCTS
    public List<ResponseProductDto> getAllProducts() {
        List<Product> products = PRODUCT_REPOSITORY.findAll();
        if (products.isEmpty()){
            throw new RuntimeException("Empty list");
        }

        return products.stream()
                .map(product -> MapperProductDto.fromEntity(product))
                .toList();
    }
}
