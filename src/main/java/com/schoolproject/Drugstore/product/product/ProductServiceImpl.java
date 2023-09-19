package com.schoolproject.Drugstore.product.product;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.brand.BrandRepository;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.category.ProductCategoryRepository;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormRepository;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyFor;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyForRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapperDto productMapperDto;

    private final ProductCategoryRepository productCategoryRepository;
    private final BrandRepository brandRepository;
    private final ProductDosageFormRepository productDosageFormRepository;
    private final ProductSpecifyForRepository productSpecifyForRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapperDto productMapperDto, ProductCategoryRepository productCategoryRepository, BrandRepository brandRepository, ProductDosageFormRepository productDosageFormRepository, ProductSpecifyForRepository productSpecifyForRepository) {
        this.productRepository = productRepository;
        this.productMapperDto = productMapperDto;
        this.productCategoryRepository = productCategoryRepository;
        this.brandRepository = brandRepository;
        this.productDosageFormRepository = productDosageFormRepository;
        this.productSpecifyForRepository = productSpecifyForRepository;
    }


    @Override
    public Collection<ProductDto> getAllProducts(){
        return productRepository.findAll().stream().map(product -> productMapperDto.toDTO(product)).toList();
    }

    @Override
    public ProductDto getProductById(Integer id){
        Optional<Product> product =  productRepository.findById(id);
        if (product.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return productMapperDto.toDTO(productRepository.getReferenceById(id));
    }

    @Override
    public ProductDto updateProduct(ProductCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        Product convertProduct = productMapperDto.toProduct(newProduct);
        Product updateProduct = productRepository.findById(id).map(product ->
                {
                    Collection<ProductSpecifyFor> productSpecifyFors = product.getProductSpecifyFors();
                    newProduct.getProductSpecifyFors().stream().map(specify -> productSpecifyForRepository.getReferenceById(specify)).forEach(productSpecifyFors::add);
                    product.setProductSpecifyFors(productSpecifyFors);


                    return productRepository.save(product);
                }).orElseGet(()->{
            convertProduct.setId(id);
                return productRepository.save(convertProduct);
        });
        return productMapperDto.toDTO(updateProduct);

    }

    @Override
    public ProductDto addProduct(ProductCreationDto productCreationDto){
        Product product = productMapperDto.toProduct(productCreationDto);
        Integer brandId = productCreationDto.getBrandId();
        Integer productCategoryId = productCreationDto.getProductCategoryId();
        Integer productDosageFormId = productCreationDto.getProductDosageFormId();

        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productCategoryId);
        Optional<Brand> brand = brandRepository.findById(brandId);
        Optional<ProductDosageForm> productDosageForm = productDosageFormRepository.findById(productDosageFormId);

        if (productCategory.isEmpty()){
            throw new DataNotFoundException(productCategoryId, ProductCategory.class.getSimpleName());
        }
        if (brand.isEmpty()){
            throw new DataNotFoundException(brandId, Brand.class.getSimpleName());
        }
        if (productDosageForm.isEmpty()){
            throw new DataNotFoundException(productDosageFormId, ProductDosageForm.class.getSimpleName());
        }

        product.setProductCategory(productCategoryRepository.getReferenceById(productCategoryId));
        product.setBrand(brandRepository.getReferenceById(brandId));
        product.setProductDosageForm(productDosageFormRepository.getReferenceById(productDosageFormId));

        productRepository.save(product);
        return productMapperDto.toDTO(product);
    }
    @Override
    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }


}
