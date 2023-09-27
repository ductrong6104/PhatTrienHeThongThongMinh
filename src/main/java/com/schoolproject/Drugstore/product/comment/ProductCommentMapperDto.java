package com.schoolproject.Drugstore.product.comment;

import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Component
public class ProductCommentMapperDto {
    public ProductCommentDto toDTO(ProductComment productComment){
        Optional<ProductComment> subjectReply = Optional.ofNullable(productComment.getProductComment());
        return new ProductCommentDto(productComment.getId(), productComment.getSubject(), productComment.getCustomer().getFullName(), productComment.getProduct().getName(), String.valueOf(subjectReply.map(ProductComment::getSubject).orElse("")) , productComment.getDate());
    }

    public ProductComment toProductComment(ProductCommentCreationDto productCommentCreationDto){
        return new ProductComment(productCommentCreationDto.getSubject(), new Date(), new Product(), new Customer());
    }

}
