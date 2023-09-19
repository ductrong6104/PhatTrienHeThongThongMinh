package com.schoolproject.Drugstore.product.rate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRateCreationDto {
    private Integer star;
    private Date time;
    private Integer customerId;
    private Integer productId;
}
