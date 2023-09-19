package com.schoolproject.Drugstore.product.use;

import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyFor;
import com.schoolproject.Drugstore.product.use.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductUseForServiceImpl implements ProductUseForService {
    private final ProductUseForRepository productUseForRepository;
    private final ProductUseForMapperDto productUseForMapperDto;

    @Autowired
    public ProductUseForServiceImpl(ProductUseForRepository productUseForRepository, ProductUseForMapperDto productUseForMapperDto) {
        this.productUseForRepository = productUseForRepository;
        this.productUseForMapperDto = productUseForMapperDto;
    }

    @Override
    public Collection<ProductUseForDto> getAllProductUseFors(){
        return productUseForRepository.findAll().stream().map(productUseFor -> productUseForMapperDto.toDTO(productUseFor)).toList();
    }

    @Override
    public ProductUseForDto getProductUseForById(Integer id){
        Optional<ProductUseFor> productUseFor =  productUseForRepository.findById(id);
        if (productUseFor.isEmpty()){
            throw new DataNotFoundException(id, ProductUseFor.class.getSimpleName());

        }
        return productUseForMapperDto.toDTO(productUseForRepository.getReferenceById(id));
    }

    @Override
    public ProductUseForDto updateProductUseFor(ProductUseForCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductUseFor convertProductUseFor = productUseForMapperDto.toProductUseFor(newProduct);
        ProductUseFor updateProduct = productUseForRepository.findById(id).map(productUseFor ->
                {


                    return productUseForRepository.save(productUseFor);
                }).orElseGet(()->{
            convertProductUseFor.setId(id);
                return productUseForRepository.save(convertProductUseFor);
        });
        return productUseForMapperDto.toDTO(updateProduct);

    }

    @Override
    public ProductUseForDto addProductUseFor(ProductUseForCreationDto productUseForCreationDto){
        ProductUseFor productUseFor = productUseForMapperDto.toProductUseFor(productUseForCreationDto);
        productUseForRepository.save(productUseFor);
        return productUseForMapperDto.toDTO(productUseFor);
    }
    @Override
    public void deleteProductUseFor(Integer id){
        productUseForRepository.deleteById(id);
    }


}
