package com.schoolproject.Drugstore.product.dosageform;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDosageFormService {
    private final ProductDosageFormRepository productDosageFormRepository;
    @Autowired
    public ProductDosageFormService(ProductDosageFormRepository productDosageFormRepository) {
        this.productDosageFormRepository = productDosageFormRepository;
    }

    public Collection<ProductDosageForm> getAllProductDosageForms(){
        List<ProductDosageForm> productDosageForms = productDosageFormRepository.findAll();
        for (ProductDosageForm b: productDosageForms){
            System.out.println(b);
        }
        return productDosageForms;
    }

    public ProductDosageForm getProductDosageFormById(Integer id){
        Optional<ProductDosageForm> productDosageForm =  productDosageFormRepository.findById(id);
        if (productDosageForm.isEmpty()){
            throw new DataNotFoundException(id, ProductDosageForm.class.getSimpleName());

        }
        return productDosageFormRepository.getReferenceById(id);
    }

    public ProductDosageForm updateProductDosageForm(ProductDosageForm newProductDosageForm, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductDosageForm updateProductDosageForm = productDosageFormRepository.findById(id).map(productDosageForm ->
                {

                    return productDosageFormRepository.save(productDosageForm);
                }).orElseGet(()->{
            newProductDosageForm.setId(id);
            return productDosageFormRepository.save(newProductDosageForm);
        });

        return updateProductDosageForm;
    }
    public ProductDosageForm addProductDosageForm(ProductDosageForm productDosageForm){
        return productDosageFormRepository.save(productDosageForm);
    }

    public void deleteProductDosageForm(Integer id){
        productDosageFormRepository.deleteById(id);
    }


}
