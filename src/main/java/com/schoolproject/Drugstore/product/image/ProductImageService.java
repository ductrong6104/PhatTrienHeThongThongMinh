package com.schoolproject.Drugstore.product.image;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;
    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    public Collection<ProductImage> getAllProductImages(){
        List<ProductImage> productImages = productImageRepository.findAll();
        for (ProductImage b: productImages){
            System.out.println(b);
        }
        return productImages;
    }

    public ProductImage getProductImageById(Integer id){
        Optional<ProductImage> productImage =  productImageRepository.findById(id);
        if (productImage.isEmpty()){
            throw new DataNotFoundException(id, ProductImage.class.getSimpleName());

        }
        return productImageRepository.getReferenceById(id);
    }

    public ProductImage updateProductImage(ProductImage newProductImage, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductImage updateProductImage = productImageRepository.findById(id).map(productImage ->
                {
                    
                    return productImageRepository.save(productImage);
                }).orElseGet(()->{
            newProductImage.setId(id);
            return productImageRepository.save(newProductImage);
        });

        return updateProductImage;
    }
    public ProductImage addProductImage(ProductImage productImage){
        return productImageRepository.save(productImage);
    }

    public void deleteProductImage(Integer id){
        productImageRepository.deleteById(id);
    }


}
