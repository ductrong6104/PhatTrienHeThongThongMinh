package com.schoolproject.Drugstore.product.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    private Integer id;
    private String name;
    private String description;
    private String avatar;
    private String nationName;
    private Collection<String> products;

}
