package com.schoolproject.Drugstore.product.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUnitRepository extends JpaRepository<ProductUnit, Integer> {

}
