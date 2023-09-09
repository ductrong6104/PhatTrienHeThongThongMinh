package com.schoolproject.Drugstore.product.rate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRateRepository extends JpaRepository<ProductRate, Integer> {

}
