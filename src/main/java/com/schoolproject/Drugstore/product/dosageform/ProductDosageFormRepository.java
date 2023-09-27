package com.schoolproject.Drugstore.product.dosageform;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDosageFormRepository extends JpaRepository<ProductDosageForm, Integer> {
    List<ProductDosageForm> findByName(String name);

}
