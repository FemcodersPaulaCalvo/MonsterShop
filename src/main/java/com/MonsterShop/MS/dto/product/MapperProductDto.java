package com.MonsterShop.MS.dto.product;

import com.MonsterShop.MS.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class MapperProductDto{

    public static Product toEntity (RequestProductDto productDto){
        return new Product(productDto.name(), productDto.price(), productDto.imageUrl(), productDto.featured());
    }

    public static ResponseProductDto fromEntity(Product product){
        return new ResponseProductDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), product.getRating(), product.getReviewCount(), product.isFeatured());
    }
}
