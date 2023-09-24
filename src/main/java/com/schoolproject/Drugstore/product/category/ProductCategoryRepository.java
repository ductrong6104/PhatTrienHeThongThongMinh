package com.schoolproject.Drugstore.product.category;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.Drugstore.product.group.ProductGroup;

public interface ProductCategoryRepository  extends JpaRepository<ProductCategory, Integer>{
    List<ProductCategory> findByProductGroup(ProductGroup productGroup);
}
