package com.schoolproject.Drugstore.product.type;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;
    @Autowired
    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public Collection<ProductType> getAllProductTypes(){
        List<ProductType> productTypes = productTypeRepository.findAll();
        for (ProductType b: productTypes){
            System.out.println(b);
        }
        return productTypes;
    }

    public ProductType getProductTypeById(Integer id){
        Optional<ProductType> productType =  productTypeRepository.findById(id);
        if (productType.isEmpty()){
            throw new DataNotFoundException(id, ProductType.class.getSimpleName());

        }
        return productTypeRepository.getReferenceById(id);
    }

    public ProductType updateProductType(ProductType newProductType, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductType updateProductType = productTypeRepository.findById(id).map(productType ->
                {
                    
                    return productTypeRepository.save(productType);
                }).orElseGet(()->{
            newProductType.setId(id);
            return productTypeRepository.save(newProductType);
        });

        return updateProductType;
    }
    public ProductType addProductType(ProductType productType){
        return productTypeRepository.save(productType);
    }

    public void deleteProductType(Integer id){
        productTypeRepository.deleteById(id);
    }


}
