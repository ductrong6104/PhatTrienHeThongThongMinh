package com.schoolproject.Drugstore.nation;

import org.springframework.stereotype.Component;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NationMapper {

    /*
     * Có 2 th mapper
     * 1. Tạo mới
     * - Luôn phải có type id sẵn
     * 2. Sửa đổi
     * - TypeID được gán trong service trước khi mapper
     * 
     */
    public Nation toEntity(NationDto nationDto) {
        if (nationDto == null) {
            throw new DataNotFoundException();
        }
        Nation nation = Nation.builder()
                .id(nationDto.getId())
                .name(nationDto.getName())
                .build();
        return nation;
    }

    public NationDto toDto(Nation nation) {
        if (nation == null) {
            throw new DataNotFoundException();
        }
        NationDto nationDto = NationDto.builder()
                .id(nation.getId())
                .name(nation.getName())
                .build();

        return nationDto;
    }

}
