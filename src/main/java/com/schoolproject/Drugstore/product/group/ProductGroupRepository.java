package com.schoolproject.Drugstore.product.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {
    @Query("SELECT pg FROM ProductGroup pg JOIN pg.productType pt WHERE pt.id = ?1")
    public Optional<ProductGroup> getProductGroupByTypeId(Integer typeId);
}
