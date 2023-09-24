package com.schoolproject.Drugstore.product.dosageform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDosageFormDto {
    private Integer id;
    private String name;
}
