package com.MonsterShop.MS.controller;

import com.MonsterShop.MS.dto.product.ResponseProductDto;
import com.MonsterShop.MS.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService PRODUCT_SERVICE;

    public ProductController(ProductService PRODUCT_SERVICE) {
        this.PRODUCT_SERVICE = PRODUCT_SERVICE;
    }

    @GetMapping
    public ResponseEntity<List<ResponseProductDto>> getAllProductsList(){
        List<ResponseProductDto> products = PRODUCT_SERVICE.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
