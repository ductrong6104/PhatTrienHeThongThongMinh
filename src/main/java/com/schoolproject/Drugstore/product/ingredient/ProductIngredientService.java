package com.schoolproject.Drugstore.product.ingredient;

import java.util.Collection;

public interface ProductIngredientService {
    public Collection<ProductIngredientDto> getAllProductIngredients();
    public ProductIngredientDto getProductIngredientById(Integer id);
    public ProductIngredientDto updateProductIngredient(ProductIngredientCreationDto productUnitCreationDto, Integer id);
    public ProductIngredientDto addProductIngredient(ProductIngredientCreationDto productUnitCreationDto);
    public void deleteProductIngredient(Integer id);
}
