package com.schoolproject.Drugstore.product.rate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRateRepository extends JpaRepository<ProductRate, Integer> {
    @Query("SELECT pr FROM ProductRate pr JOIN pr.product p WHERE p.id = ?1")
    Collection<ProductRate> filterRateByProduct(Integer productId);
}
