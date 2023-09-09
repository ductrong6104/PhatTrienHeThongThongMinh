package com.schoolproject.Drugstore.product.use;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUseForRepository extends JpaRepository<ProductUseFor, Integer> {

}
