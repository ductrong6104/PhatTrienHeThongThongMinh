package com.schoolproject.Drugstore.product.dosageform;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.nation.Nation;
import com.schoolproject.Drugstore.nation.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductDosageFormService {
    private final ProductDosageFormRepository productDosageFormRepository;
    private final ProductDosageFormMapperDto productDosageFormMapperDto;


    @Autowired
    public ProductDosageFormService(ProductDosageFormRepository productDosageFormRepository, ProductDosageFormMapperDto productDosageFormMapperDto) {
        this.productDosageFormRepository = productDosageFormRepository;
        this.productDosageFormMapperDto = productDosageFormMapperDto;


    }

    public Collection<ProductDosageFormDto> getAllProducts(){
        return productDosageFormRepository.findAll().stream().map(productDosageForm -> productDosageFormMapperDto.toDTO(productDosageForm)).toList();
    }

    public ProductDosageFormDto getProductById(Integer id){
        Optional<ProductDosageForm> productDosageForm =  productDosageFormRepository.findById(id);
        if (productDosageForm.isEmpty()){
            throw new DataNotFoundException(id, ProductDosageForm.class.getSimpleName());

        }
        return productDosageFormMapperDto.toDTO(productDosageFormRepository.getReferenceById(id));
    }

    public ProductDosageFormDto updateProductDosageForm(ProductDosageFormCreationDto newProductDosageForm, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductDosageForm convertProductDosageForm = productDosageFormMapperDto.toProductDosageForm(newProductDosageForm);
        ProductDosageForm updateProductDosageForm = productDosageFormRepository.findById(id).map(productDosageForm ->
                {
                // update productDosageForm existing
                    return productDosageFormRepository.save(productDosageForm);
                }).orElseGet(()->{
                    // create new productDosageForm
                convertProductDosageForm.setId(id);
                return productDosageFormRepository.save(convertProductDosageForm);
        });
        return productDosageFormMapperDto.toDTO(updateProductDosageForm);

    }
    public ProductDosageFormDto addProductDosageForm(ProductDosageFormCreationDto productDosageFormCreationDto){
        ProductDosageForm productDosageForm = productDosageFormMapperDto.toProductDosageForm(productDosageFormCreationDto);
        productDosageFormRepository.save(productDosageForm);
        return productDosageFormMapperDto.toDTO(productDosageForm);
    }

    public void deleteProductDosageForm(Integer id){
        productDosageFormRepository.deleteById(id);
    }


}
