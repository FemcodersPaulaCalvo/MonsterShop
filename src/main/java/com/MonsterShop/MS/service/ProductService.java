package com.MonsterShop.MS.service;

import com.MonsterShop.MS.dto.product.MapperProductDto;
import com.MonsterShop.MS.dto.product.RequestProductDto;
import com.MonsterShop.MS.dto.product.ResponseProductDto;
import com.MonsterShop.MS.entity.Product;
import com.MonsterShop.MS.entity.ProductReview;
import com.MonsterShop.MS.exceptions.EmptyListException;
import com.MonsterShop.MS.exceptions.NoIdProductFoundException;
import com.MonsterShop.MS.exceptions.ProductAlreadyExistException;
import com.MonsterShop.MS.repository.ProductRepository;
import com.MonsterShop.MS.repository.ProductReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository PRODUCT_REPOSITORY;
    private final ProductReviewRepository PRODUCT_REVIEW_REPOSITORY;

    public ProductService(ProductRepository PRODUCT_REPOSITORY, ProductReviewRepository PRODUCT_REVIEW_REPOSITORY) {
        this.PRODUCT_REPOSITORY = PRODUCT_REPOSITORY;
        this.PRODUCT_REVIEW_REPOSITORY = PRODUCT_REVIEW_REPOSITORY;
    }

    //  GET ALL PRODUCTS
    public List<ResponseProductDto> getAllProducts() {
        List<Product> products = PRODUCT_REPOSITORY.findAll();
        if (products.isEmpty()){
            throw new EmptyListException();
        }

        return products.stream()
                .map(product -> MapperProductDto.fromEntity(product))
                .toList();
    }

    //  GET PRODUCT BY ID
    public ResponseProductDto getProductById(Long id){
        Product productById = PRODUCT_REPOSITORY.findById(id)
                .orElseThrow(() ->  new NoIdProductFoundException(id));

        return MapperProductDto.fromEntity(productById);

    }

    //  POST NEW PRODUCT
    public ResponseProductDto createNewProduct(RequestProductDto requestProductDto){
        Optional<Product> productByName = PRODUCT_REPOSITORY.findByName(requestProductDto.name());
        if (productByName.isPresent()){
            throw new ProductAlreadyExistException(productByName.get().getName(), productByName.get().getPrice(), productByName.get().getId());
        }
        Product newProduct = MapperProductDto.toEntity(requestProductDto);
        Product savedProduct = PRODUCT_REPOSITORY.save(newProduct);
        return MapperProductDto.fromEntity(savedProduct);
    }

    //  UPDATE A PRODUCT BY ID
    public ResponseProductDto updateNewProduct(Long id, RequestProductDto requestProductDto){
        Product isExisting = PRODUCT_REPOSITORY.findById(id)
                .orElseThrow(() ->  new NoIdProductFoundException(id));
        isExisting.setName(requestProductDto.name());
        isExisting.setPrice(requestProductDto.price());
        isExisting.setImageUrl(requestProductDto.imageUrl());
        isExisting.setDescription(requestProductDto.description());
        isExisting.setFeatured(requestProductDto.featured());

        Optional<Product> productByName = PRODUCT_REPOSITORY.findByName(requestProductDto.name());
        if (productByName.isPresent()){
            throw new ProductAlreadyExistException(productByName.get().getName(), productByName.get().getPrice(), productByName.get().getId());
        }

        return MapperProductDto.fromEntity(isExisting);

    }

    //  UPDATE PRODUCT REVIEW STATS
    public Product updateProductReviewStats(Long productId) {
        Product isExisting = PRODUCT_REPOSITORY.findById(productId)
                .orElseThrow(() ->  new NoIdProductFoundException(productId));
        List<ProductReview> reviews = PRODUCT_REVIEW_REPOSITORY.findAllByProductId(productId);
        int updatedReviewCount = reviews.size();
        double averageRating = reviews.stream()
                .mapToDouble(pr -> pr.getReview().getRating())
                .average()
                .orElse(0.0);

        averageRating = Math.round(averageRating * 100.0) / 100.0;

        isExisting.setReviewCount(updatedReviewCount);
        isExisting.setRating(averageRating);

        return PRODUCT_REPOSITORY.save(isExisting);
    }


    //  DELETE PRODUCT BY ID
    public void deleteProductById(Long id){
        Product isExisting = PRODUCT_REPOSITORY.findById(id)
                .orElseThrow(() ->  new NoIdProductFoundException(id));
        PRODUCT_REPOSITORY.deleteById(id);
    }

}
