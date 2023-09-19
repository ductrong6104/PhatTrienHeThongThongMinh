package com.schoolproject.Drugstore.product.specify;

import com.schoolproject.Drugstore.product.group.ProductGroup;
import com.schoolproject.Drugstore.product.product.Product;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ProductSpecifyForMapperDto {


    public ProductSpecifyForDto toDTO(ProductSpecifyFor productSpecifyFor){
        Collection<String> product = productSpecifyFor.getProducts().stream().map(Product::getName).toList();
        return new ProductSpecifyForDto(productSpecifyFor.getId(), productSpecifyFor.getName(),product);
    }

    public ProductSpecifyFor toProductSpecifyFor(ProductSpecifyForCreationDto productSpecifyForCreationDto){
        return new ProductSpecifyFor(productSpecifyForCreationDto.getName(), new ArrayList<>());
    }

}
