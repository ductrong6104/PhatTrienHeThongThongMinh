package com.schoolproject.Drugstore.product.use;

import org.springframework.stereotype.Service;

import java.util.Collection;

public interface ProductUseForService {
    public Collection<ProductUseForDto> getAllProductUseFors();
    public ProductUseForDto getProductUseForById(Integer id);
    public ProductUseForDto updateProductUseFor(ProductUseForCreationDto newProductUseFor, Integer id);
    public ProductUseForDto addProductUseFor(ProductUseForCreationDto productCreationDto);
    public void deleteProductUseFor(Integer id);
}
