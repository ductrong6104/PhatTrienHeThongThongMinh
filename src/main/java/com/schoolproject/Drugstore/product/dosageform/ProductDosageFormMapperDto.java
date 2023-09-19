package com.schoolproject.Drugstore.product.dosageform;


import com.schoolproject.Drugstore.nation.Nation;
import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductDosageFormMapperDto {

    public ProductDosageFormDto toDTO(ProductDosageForm productDosageForm){
        return new ProductDosageFormDto(productDosageForm.getId(), productDosageForm.getName(), productDosageForm.getProducts().stream().map(Product::getName).toList());
    }

    public ProductDosageForm toProductDosageForm(ProductDosageFormCreationDto productDosageFormCreationDto){
        return new ProductDosageForm(productDosageFormCreationDto.getName(),new ArrayList<>());
    }

}
