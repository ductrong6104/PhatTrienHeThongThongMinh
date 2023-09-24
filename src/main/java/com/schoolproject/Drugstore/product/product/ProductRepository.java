package com.schoolproject.Drugstore.product.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.Drugstore.brand.Brand;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);

    List<Product> findByBrand(Brand brand);

    List<Product> findByProductDosageForm(ProductDosageForm productDosageForm);

}
