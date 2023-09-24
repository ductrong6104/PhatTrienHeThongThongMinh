package com.schoolproject.Drugstore.product.group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.Drugstore.product.type.ProductType;

public interface ProductGroupRepository  extends JpaRepository<ProductGroup, Integer>{
    List<ProductGroup> findByProductType(ProductType productType);
}
