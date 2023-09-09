package com.schoolproject.Drugstore.product.specify;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecifyForRepository extends JpaRepository<ProductSpecifyFor, Integer> {

}
