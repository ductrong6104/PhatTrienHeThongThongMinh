package com.schoolproject.Drugstore.product.unit;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.brand.BrandRepository;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.category.ProductCategoryRepository;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormRepository;
import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.product.ProductRepository;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyFor;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyForRepository;
import com.schoolproject.Drugstore.product.unit.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    private final ProductUnitRepository productUnitRepository;
    private final ProductUnitMapperDto productUnitMapperDto;

    @Autowired
    public ProductUnitServiceImpl(ProductUnitRepository productUnitRepository, ProductUnitMapperDto productUnitMapperDto, ProductCategoryRepository productUnitCategoryRepository, BrandRepository brandRepository, ProductDosageFormRepository productUnitDosageFormRepository, ProductSpecifyForRepository productUnitSpecifyForRepository) {
        this.productUnitRepository = productUnitRepository;
        this.productUnitMapperDto = productUnitMapperDto;

    }


    @Override
    public Collection<ProductUnitDto> getAllProductUnits(){
        return productUnitRepository.findAll().stream().map(productUnit -> productUnitMapperDto.toDTO(productUnit)).toList();
    }

    @Override
    public ProductUnitDto getProductUnitById(Integer id){
        Optional<ProductUnit> productUnit =  productUnitRepository.findById(id);
        if (productUnit.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return productUnitMapperDto.toDTO(productUnitRepository.getReferenceById(id));
    }

    @Override
    public ProductUnitDto updateProductUnit(ProductUnitCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductUnit convertProduct = productUnitMapperDto.toProductUnit(newProduct);
        ProductUnit updateProduct = productUnitRepository.findById(id).map(productUnit ->
                {


                    return productUnitRepository.save(productUnit);
                }).orElseGet(()->{
            convertProduct.setId(id);
                return productUnitRepository.save(convertProduct);
        });
        return productUnitMapperDto.toDTO(updateProduct);

    }

    @Override
    public ProductUnitDto addProductUnit(ProductUnitCreationDto productUnitCreationDto){
        ProductUnit productUnit = productUnitMapperDto.toProductUnit(productUnitCreationDto);
        productUnitRepository.save(productUnit);
        return productUnitMapperDto.toDTO(productUnit);
    }
    @Override
    public void deleteProductUnit(Integer id){
        productUnitRepository.deleteById(id);
    }


}
