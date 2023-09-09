package com.schoolproject.Drugstore.product.rate;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductRateService {
    private final ProductRateRepository productRateRepository;
    @Autowired
    public ProductRateService(ProductRateRepository productRateRepository) {
        this.productRateRepository = productRateRepository;
    }

    public Collection<ProductRate> getAllProductRates(){
        List<ProductRate> productRates = productRateRepository.findAll();
        for (ProductRate b: productRates){
            System.out.println(b);
        }
        return productRates;
    }

    public ProductRate getProductRateById(Integer id){
        Optional<ProductRate> productRate =  productRateRepository.findById(id);
        if (productRate.isEmpty()){
            throw new DataNotFoundException(id, ProductRate.class.getSimpleName());

        }
        return productRateRepository.getReferenceById(id);
    }

    public ProductRate updateProductRate(ProductRate newProductRate, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductRate updateProductRate = productRateRepository.findById(id).map(productRate ->
                {
                    
                    return productRateRepository.save(productRate);
                }).orElseGet(()->{
            newProductRate.setId(id);
            return productRateRepository.save(newProductRate);
        });

        return updateProductRate;
    }
    public ProductRate addProductRate(ProductRate productRate){
        return productRateRepository.save(productRate);
    }

    public void deleteProductRate(Integer id){
        productRateRepository.deleteById(id);
    }


}
