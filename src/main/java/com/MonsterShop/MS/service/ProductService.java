package com.MonsterShop.MS.service;

import com.MonsterShop.MS.dto.product.MapperProductDto;
import com.MonsterShop.MS.dto.product.RequestProductDto;
import com.MonsterShop.MS.dto.product.ResponseProductDto;
import com.MonsterShop.MS.entity.Product;
import com.MonsterShop.MS.entity.ProductReview;
import com.MonsterShop.MS.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //  GET PRODUCT BY ID
    public ResponseProductDto getProductById(Long id){
        Product productById = PRODUCT_REPOSITORY.findById(id)
                .orElseThrow(() ->  new RuntimeException("Id not found"));

        return MapperProductDto.fromEntity(productById);

    }

    //  POST NEW PRODUCT
    public ResponseProductDto createNewProduct(RequestProductDto requestProductDto){
        Optional<Product> productByName = PRODUCT_REPOSITORY.findByName(requestProductDto.name());
        if (productByName.isPresent()){
            throw new RuntimeException("This monster already exist: " + productByName.get().getName());
        }
        Product newProduct = MapperProductDto.toEntity(requestProductDto);
        Product savedProduct = PRODUCT_REPOSITORY.save(newProduct);
        return MapperProductDto.fromEntity(savedProduct);
    }
}
