package com.schoolproject.Drugstore.product.product;

import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    public Collection<ProductDto> getAllProducts();
    public ProductDto getProductById(Integer id);
    public ProductDto updateProduct(ProductCreationDto newProduct, Integer id);
    public ProductDto addProduct(ProductCreationDto productCreationDto);
    public void deleteProduct(Integer id);

    public Collection<ProductDto> searchProductsLikeText(String text);

    public Collection<ProductDto> filterProductsByType(Integer typeId);
    public Collection<ProductDto> filterProductsByGroup(Integer groupId);
    public Collection<ProductDto> filterProductsByCategory(Integer categoryId);
    public Collection<ProductDto> filterProductsByBrand(Integer brandId);
    public Collection<ProductDto> filterProductsByProductUseFor(Integer productUseForId);
    public Collection<ProductDto> filterProductsByProductSpecifyFor(Integer productSpecifyForId);
}
