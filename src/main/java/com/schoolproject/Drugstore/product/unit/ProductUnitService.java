package com.schoolproject.Drugstore.product.unit;

import java.util.Collection;

public interface ProductUnitService {
    public Collection<ProductUnitDto> getAllProductUnits();
    public ProductUnitDto getProductUnitById(Integer id);
    public ProductUnitDto updateProductUnit(ProductUnitCreationDto productUnitCreationDto, Integer id);
    public ProductUnitDto addProductUnit(ProductUnitCreationDto productUnitCreationDto);
    public void deleteProductUnit(Integer id);
}
