package com.schoolproject.Drugstore.product.specify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSpecifyForDto {

    private Integer id;
    private String name;

}
