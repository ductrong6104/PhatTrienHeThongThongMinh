package com.schoolproject.Drugstore.product.group;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.category.*;
import com.schoolproject.Drugstore.product.type.ProductType;
import com.schoolproject.Drugstore.product.type.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface ProductGroupService {


    public Collection<ProductGroupDto> getAllProducts();

    public ProductGroupDto getProductById(Integer id);

    public ProductGroupDto updateProductGroup(ProductGroupCreationDto newProductGroup, Integer id);
    public ProductGroupDto addProductGroup(ProductGroupCreationDto productGroupCreationDto);

    public void deleteProductGroup(Integer id);
    public Collection<ProductGroupDto> getProductsByTypeId(Integer typeId);

}
