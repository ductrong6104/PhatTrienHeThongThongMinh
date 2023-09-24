package com.schoolproject.Drugstore.product.product;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import com.schoolproject.Drugstore.brand.Brand;
import com.schoolproject.Drugstore.brand.BrandRepository;
import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.category.ProductCategoryRepository;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormRepository;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductCategoryRepository productCategoryRepository;
    private final BrandRepository brandRepository;
    private final ProductDosageFormRepository productDosageFormRepository;

    public Product toEntity(ProductDto productDto) {
        if (productDto == null) {
            throw new DataNotFoundException();
        }

        ProductCategory productCategory = productCategoryRepository
                .findById(productDto.getCategoryId())
                .get();

        Brand brand = brandRepository
                .findById(productDto.getBrandId())
                .get();

        ProductDosageForm productDosageForm = productDosageFormRepository
                .findById(productDto.getDosageFormId())
                .get();

        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .uses(productDto.getUses())
                .userManual(productDto.getUserManual())
                .sideEffects(productDto.getSideEffects())
                .storage(productDto.getStorage())
                .note(productDto.getNote())
                .totalNumber(productDto.getTotalNumber())
                .soldNumber(productDto.getSoldNumber())
                .productCategory(productCategory)
                .brand(brand)
                .productDosageForm(productDosageForm)
                .build();

        return product;
    }

    public ProductDto toDto(Product product) {
        if (product == null) {
            throw new DataNotFoundException();
        }
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .uses(product.getUses())
                .userManual(product.getUserManual())
                .sideEffects(product.getSideEffects())
                .storage(product.getStorage())
                .note(product.getNote())
                .totalNumber(product.getTotalNumber())
                .soldNumber(product.getSoldNumber())
                .categoryId(product.getProductCategory().getId())
                .brandId(product.getBrand().getId())
                .dosageFormId(product.getProductDosageForm().getId())
                .build();

        return productDto;
    }

}
