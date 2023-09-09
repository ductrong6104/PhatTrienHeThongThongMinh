package com.schoolproject.Drugstore.product.brand;

import com.schoolproject.Drugstore.order.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
