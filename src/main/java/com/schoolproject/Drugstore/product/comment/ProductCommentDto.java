package com.schoolproject.Drugstore.product.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCommentDto {
    private Integer id;
    private String subject;
    private String customerName;
    private String productName;
    private String replyForComment;
}
