package com.schoolproject.Drugstore.product.category;


import com.schoolproject.Drugstore.exception.DataNotFoundException;

import com.schoolproject.Drugstore.product.group.ProductGroup;
import com.schoolproject.Drugstore.product.group.ProductGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


public interface ProductCategoryService {


    public Collection<ProductCategoryDto> getAllProducts();

    public ProductCategoryDto getProductById(Integer id);

    public ProductCategoryDto updateProductCategory(ProductCategoryCreationDto newProductCategory, Integer id);
    public ProductCategoryDto addProductCategory(ProductCategoryCreationDto productCategoryCreationDto);

    public void deleteProductCategory(Integer id);
    public Collection<ProductCategoryDto> filterCategoryByType(Integer typeId);
    public Collection<ProductCategoryDto> filterCategoryByGroup(Integer groupId);

}
