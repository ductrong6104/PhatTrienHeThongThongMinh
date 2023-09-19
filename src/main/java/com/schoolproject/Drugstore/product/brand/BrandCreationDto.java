package com.schoolproject.Drugstore.product.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandCreationDto {
    private String name;
    private String description;
    private String avatar;
    private Integer nationId;
    private Collection<String> products;
}
