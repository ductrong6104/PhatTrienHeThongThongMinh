package com.schoolproject.Drugstore.brand;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.nation.Nation;
import com.schoolproject.Drugstore.nation.NationRepository;

@Component
@RequiredArgsConstructor
public class BrandMapper {

    private final NationRepository nationRepository;

    /*
     * Có 2 th mapper
     * 1. Tạo mới
     * - Luôn phải có type id sẵn
     * 2. Sửa đổi
     * - TypeID được gán trong service trước khi mapper
     * 
     */
    public Brand toEntity(BrandDto brandDto) {
        if (brandDto == null) {
            throw new DataNotFoundException();
        }

        Nation nation = nationRepository
                .findById(brandDto.getNationId())
                .get();

        Brand brand = Brand.builder()
                .id(brandDto.getId())
                .name(brandDto.getName())
                .description(brandDto.getDescription())
                .avatar(brandDto.getAvatar())
                .nation(nation)
                .build();
        return brand;
    }

    public BrandDto toDto(Brand brand) {
        if (brand == null) {
            throw new DataNotFoundException();
        }
        BrandDto brandDto = BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .avatar(brand.getAvatar())
                .nationId(brand.getNation().getId())
                .build();

        return brandDto;
    }

}
