package com.schoolproject.Drugstore.product.rate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Collection;
import java.util.Date;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRateDto {
    private Integer id;
    private Integer star;
    private Date time;
    private String customerName;
    private String productName;
}
