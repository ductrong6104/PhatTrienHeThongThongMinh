package com.schoolproject.Drugstore.product.dosageform;

import org.springframework.stereotype.Component;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductDosageFormMapper {

    /*
     * Có 2 th mapper
     * 1. Tạo mới
     * - Luôn phải có type id sẵn
     * 2. Sửa đổi
     * - TypeID được gán trong service trước khi mapper
     * 
     */
    public ProductDosageForm toEntity(ProductDosageFormDto productDosageFormDto) {
        if (productDosageFormDto == null) {
            throw new DataNotFoundException();
        }
        ProductDosageForm productDosageForm = ProductDosageForm.builder()
                .id(productDosageFormDto.getId())
                .name(productDosageFormDto.getName())
                .build();
        return productDosageForm;
    }

    public ProductDosageFormDto toDto(ProductDosageForm productDosageForm) {
        if (productDosageForm == null) {
            throw new DataNotFoundException();
        }
        ProductDosageFormDto productDosageFormDto = ProductDosageFormDto.builder()
                .id(productDosageForm.getId())
                .name(productDosageForm.getName())
                .build();

        return productDosageFormDto;
    }

}
