package com.schoolproject.Drugstore.product.product;

import com.schoolproject.Drugstore.product.image.ProductImage;
import com.schoolproject.Drugstore.product.ingredient.ProductIngredient;
import com.schoolproject.Drugstore.product.unit.ProductUnit;
import com.schoolproject.Drugstore.product.use.ProductUseFor;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.List;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String productName;
    private String description;
    private String uses;
    private String userManual;
    private String sideEffects;
    private String storage;
    private String note;
    private String categoryName;
    private String brand;
    private String dosageFormName;
    private Collection<String> productSpecifyFors;
    private Collection<String> productUseFors;
    private Collection<String> productUnits;
    private Collection<String> productIngredients;
    private Collection<String> productImages;


}
