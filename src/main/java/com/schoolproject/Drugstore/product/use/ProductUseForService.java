package com.schoolproject.Drugstore.product.use;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductUseForService {
    private final ProductUseForRepository productUseForRepository;
    @Autowired
    public ProductUseForService(ProductUseForRepository productUseForRepository) {
        this.productUseForRepository = productUseForRepository;
    }

    public Collection<ProductUseFor> getAllProductUseFors(){
        List<ProductUseFor> productUseFors = productUseForRepository.findAll();
        for (ProductUseFor b: productUseFors){
            System.out.println(b);
        }
        return productUseFors;
    }

    public ProductUseFor getProductUseForById(Integer id){
        Optional<ProductUseFor> productUseFor =  productUseForRepository.findById(id);
        if (productUseFor.isEmpty()){
            throw new DataNotFoundException(id, ProductUseFor.class.getSimpleName());

        }
        return productUseForRepository.getReferenceById(id);
    }

    public ProductUseFor updateProductUseFor(ProductUseFor newProductUseFor, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductUseFor updateProductUseFor = productUseForRepository.findById(id).map(productUseFor ->
                {
                    
                    return productUseForRepository.save(productUseFor);
                }).orElseGet(()->{
            newProductUseFor.setId(id);
            return productUseForRepository.save(newProductUseFor);
        });

        return updateProductUseFor;
    }
    public ProductUseFor addProductUseFor(ProductUseFor productUseFor){
        return productUseForRepository.save(productUseFor);
    }

    public void deleteProductUseFor(Integer id){
        productUseForRepository.deleteById(id);
    }


}
