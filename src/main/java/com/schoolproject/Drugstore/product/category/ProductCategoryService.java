package com.schoolproject.Drugstore.product.category;


import com.schoolproject.Drugstore.exception.DataNotFoundException;

import com.schoolproject.Drugstore.product.group.ProductGroup;
import com.schoolproject.Drugstore.product.group.ProductGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapperDto productCategoryMapperDto;
    private final ProductGroupRepository productGroupRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ProductCategoryMapperDto productCategoryMapperDto, ProductGroupRepository productGroupRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapperDto = productCategoryMapperDto;
        this.productGroupRepository = productGroupRepository;
    }

    public Collection<ProductCategoryDto> getAllProducts(){
        return productCategoryRepository.findAll().stream().map(productCategory -> productCategoryMapperDto.toDTO(productCategory)).toList();
    }

    public ProductCategoryDto getProductById(Integer id){
        Optional<ProductCategory> productCategory =  productCategoryRepository.findById(id);
        if (productCategory.isEmpty()){
            throw new DataNotFoundException(id, ProductCategory.class.getSimpleName());

        }
        return productCategoryMapperDto.toDTO(productCategoryRepository.getReferenceById(id));
    }

    public ProductCategoryDto updateProductCategory(ProductCategoryCreationDto newProductCategory, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductCategory convertProductCategory = productCategoryMapperDto.toProductCategory(newProductCategory);
        ProductCategory updateProductCategory = productCategoryRepository.findById(id).map(productCategory ->
                {
                // update productCategory existing
                    return productCategoryRepository.save(productCategory);
                }).orElseGet(()->{
                    // create new productCategory
                convertProductCategory.setId(id);
                return productCategoryRepository.save(convertProductCategory);
        });
        return productCategoryMapperDto.toDTO(updateProductCategory);

    }
    public ProductCategoryDto addProductCategory(ProductCategoryCreationDto productCategoryCreationDto){
        ProductCategory productCategory = productCategoryMapperDto.toProductCategory(productCategoryCreationDto);
        Optional<ProductGroup> productGroup = productGroupRepository.findById(productCategoryCreationDto.getGroupId());
        if (productGroup.isEmpty()){
            throw new DataNotFoundException(productCategoryCreationDto.getGroupId(), ProductCategory.class.getSimpleName());
        }
        productCategory.setProductGroup(productGroupRepository.getReferenceById(productCategoryCreationDto.getGroupId()));
        productCategoryRepository.save(productCategory);
        return productCategoryMapperDto.toDTO(productCategory);
    }

    public void deleteProductCategory(Integer id){
        productCategoryRepository.deleteById(id);
    }


}
