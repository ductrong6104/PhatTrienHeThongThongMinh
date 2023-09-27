package com.schoolproject.Drugstore.product.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto {

    private List<Integer> specifyForId;
    private List<Integer> useForId;
    private List<Integer> ingredientId;
    private List<Integer> unitId;

}
