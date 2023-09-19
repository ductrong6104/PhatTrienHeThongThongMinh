package com.schoolproject.Drugstore.product.product;

import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.image.ProductImage;
import com.schoolproject.Drugstore.product.ingredient.ProductIngredient;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyFor;
import com.schoolproject.Drugstore.product.unit.ProductUnit;
import com.schoolproject.Drugstore.product.use.ProductUseFor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ProductMapperDto {
    public ProductDto toDTO(Product product){
        Collection<String> productSpecifyFors = product.getProductSpecifyFors().stream().map(ProductSpecifyFor::getName).collect(Collectors.toList());
        Collection<String> productUseFors = product.getProductUseFors().stream().map(ProductUseFor::getName).collect(Collectors.toList());
        Collection<String> productUnits = product.getProductUnits().stream().map(ProductUnit::getName).collect(Collectors.toList());
        Collection<String> productIngredients = product.getProductIngredients().stream().map(ProductIngredient::getName).collect(Collectors.toList());
        Collection<String> productImages = product.getProductImages().stream().map(ProductImage::getName).collect(Collectors.toList());
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getUses(),
                product.getUserManual(),
                product.getSideEffects(),
                product.getStorage(),
                product.getNote(),
                product.getProductCategory().getName(),
                product.getBrand().getName(),
                product.getProductDosageForm().getName(),
                productSpecifyFors,
                productUseFors,
                productUnits,
                productIngredients,
                productImages);
    }

    public Product toProduct(ProductCreationDto productCreationDto){

        Product product = new Product(
                productCreationDto.getProductName(),
                productCreationDto.getDescription(),
                productCreationDto.getUses(),
                productCreationDto.getUserManual(),
                productCreationDto.getSideEffects(),
                productCreationDto.getStorage(),
                productCreationDto.getNote(),
                productCreationDto.getTotalNumber(),
                productCreationDto.getSoldNumber(),
                new ProductCategory(),
                new Brand(),
                new ProductDosageForm(),
                new ArrayList<ProductSpecifyFor>(),
                new ArrayList<ProductUseFor>(),
                new ArrayList<ProductUnit>(),
                new ArrayList<ProductIngredient>(),
                new ArrayList<ProductImage>());
        return product;
    }

}
