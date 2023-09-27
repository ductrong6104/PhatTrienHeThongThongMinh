package com.schoolproject.Drugstore.product.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCommentCreationDto {
    // khong can id
    private String subject;
    private Integer customerId;
    private Integer productId;
    private Integer replyForId;
}
