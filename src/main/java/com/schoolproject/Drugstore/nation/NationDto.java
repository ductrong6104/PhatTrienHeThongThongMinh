package com.schoolproject.Drugstore.nation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NationDto {
    private Integer id;
    private String nationName;
    private Collection<String> brandNames;

}
