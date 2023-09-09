package com.schoolproject.Drugstore.product.specify;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSpecifyForService {
    private final ProductSpecifyForRepository productSpecifyForRepository;
    @Autowired
    public ProductSpecifyForService(ProductSpecifyForRepository productSpecifyForRepository) {
        this.productSpecifyForRepository = productSpecifyForRepository;
    }

    public Collection<ProductSpecifyFor> getAllProductSpecifyFors(){
        List<ProductSpecifyFor> productSpecifyFors = productSpecifyForRepository.findAll();
        for (ProductSpecifyFor b: productSpecifyFors){
            System.out.println(b);
        }
        return productSpecifyFors;
    }

    public ProductSpecifyFor getProductSpecifyForById(Integer id){
        Optional<ProductSpecifyFor> productSpecifyFor =  productSpecifyForRepository.findById(id);
        if (productSpecifyFor.isEmpty()){
            throw new DataNotFoundException(id, ProductSpecifyFor.class.getSimpleName());

        }
        return productSpecifyForRepository.getReferenceById(id);
    }

    public ProductSpecifyFor updateProductSpecifyFor(ProductSpecifyFor newProductSpecifyFor, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductSpecifyFor updateProductSpecifyFor = productSpecifyForRepository.findById(id).map(productSpecifyFor ->
                {
                    
                    return productSpecifyForRepository.save(productSpecifyFor);
                }).orElseGet(()->{
            newProductSpecifyFor.setId(id);
            return productSpecifyForRepository.save(newProductSpecifyFor);
        });

        return updateProductSpecifyFor;
    }
    public ProductSpecifyFor addProductSpecifyFor(ProductSpecifyFor productSpecifyFor){
        return productSpecifyForRepository.save(productSpecifyFor);
    }

    public void deleteProductSpecifyFor(Integer id){
        productSpecifyForRepository.deleteById(id);
    }


}
