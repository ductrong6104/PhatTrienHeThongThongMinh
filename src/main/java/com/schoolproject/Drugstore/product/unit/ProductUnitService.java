package com.schoolproject.Drugstore.product.unit;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductUnitService {
    private final ProductUnitRepository productUnitRepository;
    @Autowired
    public ProductUnitService(ProductUnitRepository productUnitRepository) {
        this.productUnitRepository = productUnitRepository;
    }

    public Collection<ProductUnit> getAllProductUnits(){
        List<ProductUnit> productUnits = productUnitRepository.findAll();
        for (ProductUnit b: productUnits){
            System.out.println(b);
        }
        return productUnits;
    }

    public ProductUnit getProductUnitById(Integer id){
        Optional<ProductUnit> productUnit =  productUnitRepository.findById(id);
        if (productUnit.isEmpty()){
            throw new DataNotFoundException(id, ProductUnit.class.getSimpleName());

        }
        return productUnitRepository.getReferenceById(id);
    }

    public ProductUnit updateProductUnit(ProductUnit newProductUnit, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductUnit updateProductUnit = productUnitRepository.findById(id).map(productUnit ->
                {
                    
                    return productUnitRepository.save(productUnit);
                }).orElseGet(()->{
            newProductUnit.setId(id);
            return productUnitRepository.save(newProductUnit);
        });

        return updateProductUnit;
    }
    public ProductUnit addProductUnit(ProductUnit productUnit){
        return productUnitRepository.save(productUnit);
    }

    public void deleteProductUnit(Integer id){
        productUnitRepository.deleteById(id);
    }


}
