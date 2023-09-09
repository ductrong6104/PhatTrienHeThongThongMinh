package com.schoolproject.Drugstore.product.dosageform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDosageFormRepository extends JpaRepository<ProductDosageForm, Integer> {

}
