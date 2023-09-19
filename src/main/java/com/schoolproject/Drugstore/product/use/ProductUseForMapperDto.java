package com.schoolproject.Drugstore.product.use;

import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ProductUseForMapperDto {
    public ProductUseForDto toDTO(ProductUseFor productUseFor){
        return new ProductUseForDto(productUseFor.getId(), productUseFor.getName(), productUseFor.getProducts().stream().map(Product::getName).toList());
    }

    public ProductUseFor toProductUseFor(ProductUseForCreationDto productCreationDto){
        return new ProductUseFor(productCreationDto.getName(), new ArrayList<>());
    }

}
