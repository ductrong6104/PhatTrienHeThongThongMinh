package com.schoolproject.Drugstore.product.use;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUseForDto {

    private Integer id;
    private String name;

}
