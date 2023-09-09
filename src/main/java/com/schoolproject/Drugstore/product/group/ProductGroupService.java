package com.schoolproject.Drugstore.product.group;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    @Autowired
    public ProductGroupService(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    public Collection<ProductGroup> getAllProductGroups(){
        List<ProductGroup> productGroups = productGroupRepository.findAll();
        for (ProductGroup b: productGroups){
            System.out.println(b);
        }
        return productGroups;
    }

    public ProductGroup getProductGroupById(Integer id){
        Optional<ProductGroup> productGroup =  productGroupRepository.findById(id);
        if (productGroup.isEmpty()){
            throw new DataNotFoundException(id, ProductGroup.class.getSimpleName());

        }
        return productGroupRepository.getReferenceById(id);
    }

    public ProductGroup updateProductGroup(ProductGroup newProductGroup, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductGroup updateProductGroup = productGroupRepository.findById(id).map(productGroup ->
                {
                    
                    return productGroupRepository.save(productGroup);
                }).orElseGet(()->{
            newProductGroup.setId(id);
            return productGroupRepository.save(newProductGroup);
        });

        return updateProductGroup;
    }
    public ProductGroup addProductGroup(ProductGroup productGroup){
        return productGroupRepository.save(productGroup);
    }

    public void deleteProductGroup(Integer id){
        productGroupRepository.deleteById(id);
    }


}
