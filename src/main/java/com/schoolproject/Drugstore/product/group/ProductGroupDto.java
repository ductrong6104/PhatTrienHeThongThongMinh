package com.schoolproject.Drugstore.product.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGroupDto {
    private Integer id;
    private String name;
    private Integer typeId;
}
