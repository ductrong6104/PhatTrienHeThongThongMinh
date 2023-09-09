package com.schoolproject.Drugstore.product.ingredient;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductIngredientService {
    private final ProductIngredientRepository productIngredientRepository;
    @Autowired
    public ProductIngredientService(ProductIngredientRepository productIngredientRepository) {
        this.productIngredientRepository = productIngredientRepository;
    }

    public Collection<ProductIngredient> getAllProductIngredients(){
        List<ProductIngredient> productIngredients = productIngredientRepository.findAll();
        for (ProductIngredient b: productIngredients){
            System.out.println(b);
        }
        return productIngredients;
    }

    public ProductIngredient getProductIngredientById(Integer id){
        Optional<ProductIngredient> productIngredient =  productIngredientRepository.findById(id);
        if (productIngredient.isEmpty()){
            throw new DataNotFoundException(id, ProductIngredient.class.getSimpleName());

        }
        return productIngredientRepository.getReferenceById(id);
    }

    public ProductIngredient updateProductIngredient(ProductIngredient newProductIngredient, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductIngredient updateProductIngredient = productIngredientRepository.findById(id).map(productIngredient ->
                {
                    
                    return productIngredientRepository.save(productIngredient);
                }).orElseGet(()->{
            newProductIngredient.setId(id);
            return productIngredientRepository.save(newProductIngredient);
        });

        return updateProductIngredient;
    }
    public ProductIngredient addProductIngredient(ProductIngredient productIngredient){
        return productIngredientRepository.save(productIngredient);
    }

    public void deleteProductIngredient(Integer id){
        productIngredientRepository.deleteById(id);
    }


}
