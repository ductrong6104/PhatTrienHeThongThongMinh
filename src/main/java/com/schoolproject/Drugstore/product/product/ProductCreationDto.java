package com.schoolproject.Drugstore.product.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
public class ProductCreationDto {
    private Integer id;
    private String productName;
    private String description;
    private String uses;
    private String userManual;
    private String sideEffects;
    private String storage;
    private String note;
    private Integer productCategory;
    private Integer brandName;
    private Integer productDosageForm;
    private Collection<Integer> productSpecifyFors;
    private Collection<Integer> productUseFors;
    private Collection<Integer> productUnits;
    private Collection<Integer> productIngredients;
    private Collection<Integer> productImages;
    private int totalNumber;
    private int soldNumber;
}
