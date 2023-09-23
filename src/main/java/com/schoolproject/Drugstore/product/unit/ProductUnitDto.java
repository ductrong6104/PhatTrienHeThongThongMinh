package com.schoolproject.Drugstore.product.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.CollationElementIterator;
import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUnitDto {
    private Integer id;
    private String productUnitName;
    private Collection<String> products;
}
