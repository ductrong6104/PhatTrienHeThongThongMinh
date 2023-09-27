package com.schoolproject.Drugstore.product.category;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    @Query("SELECT pc from ProductCategory pc JOIN pc.productGroup pg JOIN pg.productType pt WHERE pt.id = ?1 ")
    Collection<ProductCategory> filterCategoryByType(Integer typeId);
    @Query("SELECT pc from ProductCategory pc JOIN pc.productGroup pg WHERE pg.id = ?1 ")
    Collection<ProductCategory> filterCategoryByGroup(Integer groupId);
}
