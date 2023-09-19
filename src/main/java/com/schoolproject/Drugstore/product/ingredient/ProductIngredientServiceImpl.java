package com.schoolproject.Drugstore.product.ingredient;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.BrandRepository;
import com.schoolproject.Drugstore.product.category.ProductCategoryRepository;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormRepository;
import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyForRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductIngredientServiceImpl implements ProductIngredientService {
    private final ProductIngredientRepository productIngredientRepository;
    private final ProductIngredientMapperDto productIngredientMapperDto;

    @Autowired
    public ProductIngredientServiceImpl(ProductIngredientRepository productIngredientRepository, ProductIngredientMapperDto productIngredientMapperDto, ProductCategoryRepository productIngredientCategoryRepository, BrandRepository brandRepository, ProductDosageFormRepository productIngredientDosageFormRepository, ProductSpecifyForRepository productIngredientSpecifyForRepository) {
        this.productIngredientRepository = productIngredientRepository;
        this.productIngredientMapperDto = productIngredientMapperDto;

    }


    @Override
    public Collection<ProductIngredientDto> getAllProductIngredients(){
        return productIngredientRepository.findAll().stream().map(productIngredient -> productIngredientMapperDto.toDTO(productIngredient)).toList();
    }

    @Override
    public ProductIngredientDto getProductIngredientById(Integer id){
        Optional<ProductIngredient> productIngredient =  productIngredientRepository.findById(id);
        if (productIngredient.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return productIngredientMapperDto.toDTO(productIngredientRepository.getReferenceById(id));
    }

    @Override
    public ProductIngredientDto updateProductIngredient(ProductIngredientCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductIngredient convertProduct = productIngredientMapperDto.toProductIngredient(newProduct);
        ProductIngredient updateProduct = productIngredientRepository.findById(id).map(productIngredient ->
                {
                    productIngredient.setDescription(newProduct.getDescription());

                    return productIngredientRepository.save(productIngredient);
                }).orElseGet(()->{
            convertProduct.setId(id);
                return productIngredientRepository.save(convertProduct);
        });
        return productIngredientMapperDto.toDTO(updateProduct);

    }

    @Override
    public ProductIngredientDto addProductIngredient(ProductIngredientCreationDto productIngredientCreationDto){
        ProductIngredient productIngredient = productIngredientMapperDto.toProductIngredient(productIngredientCreationDto);
        productIngredientRepository.save(productIngredient);
        return productIngredientMapperDto.toDTO(productIngredient);
    }
    @Override
    public void deleteProductIngredient(Integer id){
        productIngredientRepository.deleteById(id);
    }


}
