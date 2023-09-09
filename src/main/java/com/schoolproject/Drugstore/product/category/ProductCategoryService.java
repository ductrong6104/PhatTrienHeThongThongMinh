package com.schoolproject.Drugstore.product.category;


import com.schoolproject.Drugstore.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public Collection<ProductCategory> getAllProductCategorys(){
        return productCategoryRepository.findAll();
    }

    public ProductCategory getProductCategoryById(Integer id){
        Optional<ProductCategory> productCategory =  productCategoryRepository.findById(id);
        if (productCategory.isEmpty()){
            throw new DataNotFoundException(id, ProductCategory.class.getSimpleName());

        }
        return productCategoryRepository.getReferenceById(id);
    }

    public ProductCategory updateProductCategory(ProductCategory newProductCategory, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductCategory updateProductCategory = productCategoryRepository.findById(id).map(productCategory ->
                {

                    return productCategoryRepository.save(productCategory);
                }).orElseGet(()->{
            newProductCategory.setId(id);
            return productCategoryRepository.save(newProductCategory);
        });

        return updateProductCategory;
    }
    public ProductCategory addProductCategory(ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

    public void deleteProductCategory(Integer id){
        productCategoryRepository.deleteById(id);
    }


}
