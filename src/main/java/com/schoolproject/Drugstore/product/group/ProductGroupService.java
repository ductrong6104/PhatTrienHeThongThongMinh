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
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupMapperDto productGroupMapperDto;
    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductGroupService(ProductGroupRepository productGroupRepository, ProductGroupMapperDto productGroupMapperDto, ProductTypeRepository productTypeRepository) {
        this.productGroupRepository = productGroupRepository;
        this.productGroupMapperDto = productGroupMapperDto;
        this.productTypeRepository = productTypeRepository;
    }

    public Collection<ProductGroupDto> getAllProducts(){
        return productGroupRepository.findAll().stream().map(productGroup -> productGroupMapperDto.toDTO(productGroup)).toList();
    }

    public ProductGroupDto getProductById(Integer id){
        Optional<ProductGroup> productGroup =  productGroupRepository.findById(id);
        if (productGroup.isEmpty()){
            throw new DataNotFoundException(id, ProductGroup.class.getSimpleName());

        }
        return productGroupMapperDto.toDTO(productGroupRepository.getReferenceById(id));
    }

    public ProductGroupDto updateProductGroup(ProductGroupCreationDto newProductGroup, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductGroup convertProductGroup = productGroupMapperDto.toProductGroup(newProductGroup);
        ProductGroup updateProductGroup = productGroupRepository.findById(id).map(productGroup ->
                {
                // update productGroup existing
                    return productGroupRepository.save(productGroup);
                }).orElseGet(()->{
                    // create new productGroup
                convertProductGroup.setId(id);
                return productGroupRepository.save(convertProductGroup);
        });
        return productGroupMapperDto.toDTO(updateProductGroup);

    }
    public ProductGroupDto addProductGroup(ProductGroupCreationDto productGroupCreationDto){
        ProductGroup productGroup = productGroupMapperDto.toProductGroup(productGroupCreationDto);
        Optional<ProductType> productType = productTypeRepository.findById(productGroupCreationDto.getTypeId());
        if (productType.isEmpty()){
            throw new DataNotFoundException(productGroupCreationDto.getTypeId(), ProductGroup.class.getSimpleName());
        }
        productGroup.setProductType(productTypeRepository.getReferenceById(productGroupCreationDto.getTypeId()));
        productGroupRepository.save(productGroup);
        return productGroupMapperDto.toDTO(productGroup);
    }

    public void deleteProductGroup(Integer id){
        productGroupRepository.deleteById(id);
    }
    public ProductGroupDto getProductByTypeId(Integer typeId){
        Optional<ProductGroup> productGroup =  productGroupRepository.getProductGroupByTypeId(typeId);
        if (productGroup.isEmpty()){
            throw new DataNotFoundException(typeId, ProductGroup.class.getSimpleName());

        }
        return productGroupMapperDto.toDTO(productGroup.get());
    }

}
