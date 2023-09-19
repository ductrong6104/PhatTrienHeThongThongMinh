package com.schoolproject.Drugstore.product.type;


import com.schoolproject.Drugstore.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapperDto productTypeMapperDto;


    @Autowired
    public ProductTypeService(ProductTypeRepository productTypeRepository, ProductTypeMapperDto productTypeMapperDto) {
        this.productTypeRepository = productTypeRepository;
        this.productTypeMapperDto = productTypeMapperDto;

    }

    public Collection<ProductTypeDto> getAllProducts(){
        return productTypeRepository.findAll().stream().map(productType -> productTypeMapperDto.toDTO(productType)).toList();
    }

    public ProductTypeDto getProductById(Integer id){
        Optional<ProductType> productType =  productTypeRepository.findById(id);
        if (productType.isEmpty()){
            throw new DataNotFoundException(id, ProductType.class.getSimpleName());

        }
        return productTypeMapperDto.toDTO(productTypeRepository.getReferenceById(id));
    }

    public ProductTypeDto updateProductType(ProductTypeCreationDto newProductType, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductType convertProductType = productTypeMapperDto.toProductType(newProductType);
        ProductType updateProductType = productTypeRepository.findById(id).map(productType ->
                {
                // update productType existing
                    return productTypeRepository.save(productType);
                }).orElseGet(()->{
                    // create new productType
                convertProductType.setId(id);
                return productTypeRepository.save(convertProductType);
        });
        return productTypeMapperDto.toDTO(updateProductType);

    }
    public ProductTypeDto addProductType(ProductTypeCreationDto productTypeCreationDto){
        ProductType productType = productTypeMapperDto.toProductType(productTypeCreationDto);
        productTypeRepository.save(productType);
        return productTypeMapperDto.toDTO(productType);
    }

    public void deleteProductType(Integer id){
        productTypeRepository.deleteById(id);
    }


}
