package com.MonsterShop.MS.dto.product;

import com.MonsterShop.MS.dto.review.MapperReviewDto;
import com.MonsterShop.MS.dto.review.ResponseReviewDto;
import com.MonsterShop.MS.entity.Product;
import com.MonsterShop.MS.entity.ProductReview;
import com.MonsterShop.MS.exceptions.EmptyListException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperProductDto{

    public static Product toEntity (RequestProductDto productDto){
        boolean featured = productDto.featured() ? true : productDto.featured();
        return new Product(productDto.name(), productDto.price(), productDto.imageUrl(), productDto.featured());
    }

    public static ResponseProductDto fromEntity(Product product){
        List<ResponseReviewDto> rating = product.getProductReviews()
                .stream()
                .map(ProductReview::getReview)
                .map(review ->  MapperReviewDto.fromEntity(review))
                .toList();

        return new ResponseProductDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), product.getRating(), product.getReviewCount(), product.isFeatured(), rating);
    }
}
