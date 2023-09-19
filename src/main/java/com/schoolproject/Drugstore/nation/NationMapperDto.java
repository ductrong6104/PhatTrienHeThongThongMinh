package com.schoolproject.Drugstore.nation;


import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class NationMapperDto {

    public NationDto toDTO(Nation nation){
        return new NationDto(nation.getId(), nation.getName(), nation.getBrands().stream().map(Brand::getName).toList());
    }

    public Nation toNation(NationCreationDto nationCreationDto){
        return new Nation(nationCreationDto.getName(), new ArrayList<>());
    }

}
