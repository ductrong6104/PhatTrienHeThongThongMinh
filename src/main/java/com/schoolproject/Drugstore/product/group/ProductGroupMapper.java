package com.schoolproject.Drugstore.product.group;

import org.springframework.stereotype.Component;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.product.type.ProductType;
import com.schoolproject.Drugstore.product.type.ProductTypeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductGroupMapper {

    private final ProductTypeRepository productTypeRepository;

    /*
     * Có 2 th mapper
     * 1. Tạo mới
     * - Luôn phải có type id sẵn
     * 2. Sửa đổi
     * - TypeID được gán trong service trước khi mapper
     * 
     */
    public ProductGroup toEntity(ProductGroupDto productGroupDto) {
        if (productGroupDto == null) {
            throw new DataNotFoundException();
        }

        ProductType productType = productTypeRepository
                .findById(productGroupDto.getTypeId())
                .get();

        ProductGroup productGroup = ProductGroup.builder()
                .id(productGroupDto.getId())
                .name(productGroupDto.getName())
                .productType(productType)
                .build();
        return productGroup;
    }

    public ProductGroupDto toDto(ProductGroup productGroup) {
        if (productGroup == null) {
            throw new DataNotFoundException();
        }
        ProductGroupDto productGroupDto = ProductGroupDto.builder()
                .id(productGroup.getId())
                .name(productGroup.getName())
                .typeId(productGroup.getProductType().getId())
                .build();

        return productGroupDto;
    }

}
