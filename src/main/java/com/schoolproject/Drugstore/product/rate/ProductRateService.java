package com.schoolproject.Drugstore.product.rate;

import java.util.Collection;

public interface ProductRateService {
    public Collection<ProductRateDto> getAllProductRates();
    public ProductRateDto getProductRateById(Integer id);
    public ProductRateDto updateProductRate(ProductRateCreationDto productUnitCreationDto, Integer id);
    public ProductRateDto addProductRate(ProductRateCreationDto productUnitCreationDto);
    public void deleteProductRate(Integer id);
}
