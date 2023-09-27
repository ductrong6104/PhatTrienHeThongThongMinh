package com.schoolproject.Drugstore.product.unit;

import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.image.ProductImage;
import com.schoolproject.Drugstore.product.ingredient.ProductIngredient;
import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyFor;
import com.schoolproject.Drugstore.product.unitDetail.ProductUnitDetail;
import com.schoolproject.Drugstore.product.use.ProductUseFor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ProductUnitMapperDto {
    public ProductUnitDto toDTO(ProductUnit productUnit){
        return new ProductUnitDto(productUnit.getId(), productUnit.getName(), productUnit.getProductUnitDetails().stream().map(productUnitDetail -> productUnitDetail.getProduct().getName()).toList());
    }

    public ProductUnit toProductUnit(ProductUnitCreationDto productUnitCreationDto){
        return new ProductUnit(productUnitCreationDto.getName(), new ArrayList<>());
    }

}
