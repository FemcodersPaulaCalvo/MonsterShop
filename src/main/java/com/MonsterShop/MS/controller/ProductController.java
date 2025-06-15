package com.MonsterShop.MS.controller;

import com.MonsterShop.MS.dto.product.RequestProductDto;
import com.MonsterShop.MS.dto.product.ResponseProductDto;
import com.MonsterShop.MS.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDto> getProductById(@PathVariable Long id){
        ResponseProductDto product = PRODUCT_SERVICE.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseProductDto> postNewProduct(@RequestBody RequestProductDto requestProductDto){
        ResponseProductDto newProduct = PRODUCT_SERVICE.createNewProduct(requestProductDto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
}
