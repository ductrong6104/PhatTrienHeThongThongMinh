package com.schoolproject.Drugstore.product.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private String uses;
    private String userManual;
    private String sideEffects;
    private String storage;
    private String note;
    private Integer totalNumber;
    private Integer soldNumber;
    private Integer categoryId;
    private Integer brandId;
    private Integer dosageFormId;
}
