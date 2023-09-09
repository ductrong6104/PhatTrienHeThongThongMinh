package com.schoolproject.Drugstore.order.order;

import com.schoolproject.Drugstore.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
