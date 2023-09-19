package com.schoolproject.Drugstore.product.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreationDto {
    private String productName;
    private String description;
    private String uses;
    private String userManual;
    private String sideEffects;
    private String storage;
    private String note;
    private Integer productCategoryId;
    private Integer brandId;
    private Integer productDosageFormId;
    private Collection<Integer> productSpecifyFors;
    private Collection<Integer> productUseFors;
    private Collection<Integer> productUnits;
    private Collection<Integer> productIngredients;
    private Collection<Integer> productImages;
    private int totalNumber;
    private int soldNumber;
}
