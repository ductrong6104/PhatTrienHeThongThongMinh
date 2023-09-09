package com.schoolproject.Drugstore.product.product;

import com.schoolproject.Drugstore.order.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
