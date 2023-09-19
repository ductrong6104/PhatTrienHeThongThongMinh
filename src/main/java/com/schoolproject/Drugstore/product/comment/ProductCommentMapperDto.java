package com.schoolproject.Drugstore.product.comment;

import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductCommentMapperDto {
    public ProductCommentDto toDTO(ProductComment productComment){
        return new ProductCommentDto(productComment.getId(), productComment.getSubject(), productComment.getCustomer().getFullName(), productComment.getProduct().getName(),productComment.getProductComment().getSubject());
    }

    public ProductComment toProductComment(ProductCommentCreationDto productCommentCreationDto){
        return new ProductComment(productCommentCreationDto.getSubject(), new Product(), new Customer(), new ProductComment());
    }

}
