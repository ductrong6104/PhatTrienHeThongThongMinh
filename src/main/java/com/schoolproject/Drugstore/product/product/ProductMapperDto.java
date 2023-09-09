package com.schoolproject.Drugstore.product.product;

import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.brand.BrandService;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.category.ProductCategoryService;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormService;
import com.schoolproject.Drugstore.product.image.ProductImage;
import com.schoolproject.Drugstore.product.image.ProductImageService;
import com.schoolproject.Drugstore.product.ingredient.ProductIngredient;
import com.schoolproject.Drugstore.product.ingredient.ProductIngredientService;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyFor;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyForService;
import com.schoolproject.Drugstore.product.unit.ProductUnit;
import com.schoolproject.Drugstore.product.unit.ProductUnitService;
import com.schoolproject.Drugstore.product.use.ProductUseFor;
import com.schoolproject.Drugstore.product.use.ProductUseForService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ProductMapperDto {
    private final ProductCategoryService productCategoryService;
    private final BrandService brandService;
    private final ProductDosageFormService productDosageFormService;
    private final ProductSpecifyForService ProductSpecifyForService;
    private final ProductUseForService productUseForService;
    private final ProductUnitService productUnitService;
    private final ProductIngredientService productIngredientService;
    private final ProductImageService productImageService;

    public ProductMapperDto(ProductCategoryService productCategoryService, BrandService brandService, ProductDosageFormService productDosageFormService, com.schoolproject.Drugstore.product.specify.ProductSpecifyForService productSpecifyForService, ProductUseForService productUseForService, ProductUnitService productUnitService, ProductIngredientService productIngredientService, ProductImageService productImageService) {
        this.productCategoryService = productCategoryService;
        this.brandService = brandService;
        this.productDosageFormService = productDosageFormService;
        ProductSpecifyForService = productSpecifyForService;
        this.productUseForService = productUseForService;
        this.productUnitService = productUnitService;
        this.productIngredientService = productIngredientService;
        this.productImageService = productImageService;
    }

    public ProductDto toDTO(Product product){
        Collection<String> productSpecifyFors = product.getProductSpecifyFors().stream().map(ProductSpecifyFor::getName).collect(Collectors.toList());
        Collection<String> productUseFors = product.getProductUseFors().stream().map(ProductUseFor::getName).collect(Collectors.toList());
        Collection<String> productUnits = product.getProductUnits().stream().map(ProductUnit::getName).collect(Collectors.toList());
        Collection<String> productIngredients = product.getProductIngredients().stream().map(ProductIngredient::getName).collect(Collectors.toList());
        Collection<String> productImages = product.getProductImages().stream().map(ProductImage::getName).collect(Collectors.toList());
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getUses(),
                product.getUserManual(),
                product.getSideEffects(),
                product.getStorage(),
                product.getNote(),
                product.getProductCategory().getName(),
                product.getBrand().getName(),
                product.getProductDosageForm().getName(),
                productSpecifyFors,
                productUseFors,
                productUnits,
                productIngredients,
                productImages);
    }

    public Product toProduct(ProductCreationDto productCreationDto){

        Product product = new Product(
                productCreationDto.getProductName(),
                productCreationDto.getDescription(),
                productCreationDto.getUses(),
                productCreationDto.getUserManual(),
                productCreationDto.getSideEffects(),
                productCreationDto.getStorage(),
                productCreationDto.getNote(),
                productCreationDto.getTotalNumber(),
                productCreationDto.getSoldNumber(),
                new ProductCategory(),
                new Brand(),
                new ProductDosageForm(),
                new ArrayList<ProductSpecifyFor>(),
                new ArrayList<ProductUseFor>(),
                new ArrayList<ProductUnit>(),
                new ArrayList<ProductIngredient>(),
                new ArrayList<ProductImage>());
        return product;
    }

}
