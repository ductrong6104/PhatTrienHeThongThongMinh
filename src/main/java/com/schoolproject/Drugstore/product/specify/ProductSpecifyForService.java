package com.schoolproject.Drugstore.product.specify;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductSpecifyForService {
    private final ProductSpecifyForRepository productSpecifyForRepository;
    private final ProductSpecifyForMapperDto productSpecifyForMapperDto;


    @Autowired
    public ProductSpecifyForService(ProductSpecifyForRepository productSpecifyForRepository, ProductSpecifyForMapperDto productSpecifyForMapperDto) {
        this.productSpecifyForRepository = productSpecifyForRepository;
        this.productSpecifyForMapperDto = productSpecifyForMapperDto;

    }

    public Collection<ProductSpecifyForDto> getAllProducts(){
        return productSpecifyForRepository.findAll().stream().map(productSpecifyFor -> productSpecifyForMapperDto.toDTO(productSpecifyFor)).toList();
    }

    public ProductSpecifyForDto getProductById(Integer id){
        Optional<ProductSpecifyFor> productSpecifyFor =  productSpecifyForRepository.findById(id);
        if (productSpecifyFor.isEmpty()){
            throw new DataNotFoundException(id, ProductSpecifyFor.class.getSimpleName());

        }
        return productSpecifyForMapperDto.toDTO(productSpecifyForRepository.getReferenceById(id));
    }

    public ProductSpecifyForDto updateProductSpecifyFor(ProductSpecifyForCreationDto newProductSpecifyFor, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductSpecifyFor convertProductSpecifyFor = productSpecifyForMapperDto.toProductSpecifyFor(newProductSpecifyFor);
        ProductSpecifyFor updateProductSpecifyFor = productSpecifyForRepository.findById(id).map(productSpecifyFor ->
                {
                // update productSpecifyFor existing
                    return productSpecifyForRepository.save(productSpecifyFor);
                }).orElseGet(()->{
                    // create new productSpecifyFor
                convertProductSpecifyFor.setId(id);
                return productSpecifyForRepository.save(convertProductSpecifyFor);
        });
        return productSpecifyForMapperDto.toDTO(updateProductSpecifyFor);

    }
    public ProductSpecifyForDto addProductSpecifyFor(ProductSpecifyForCreationDto productSpecifyForCreationDto){
        ProductSpecifyFor productSpecifyFor = productSpecifyForMapperDto.toProductSpecifyFor(productSpecifyForCreationDto);
        productSpecifyForRepository.save(productSpecifyFor);
        return productSpecifyForMapperDto.toDTO(productSpecifyFor);
    }

    public void deleteProductSpecifyFor(Integer id){
        productSpecifyForRepository.deleteById(id);
    }


}
