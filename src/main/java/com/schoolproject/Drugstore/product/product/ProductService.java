package com.schoolproject.Drugstore.product.product;

import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    public Collection<ProductDto> getAllProducts();
    public ProductDto getProductById(Integer id);
    public ProductDto updateProduct(ProductCreationDto newProduct, Integer id);
    public ProductDto addProduct(ProductCreationDto productCreationDto);
    public void deleteProduct(Integer id);
}
