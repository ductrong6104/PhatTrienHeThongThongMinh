package com.schoolproject.Drugstore.order.status;

import com.schoolproject.Drugstore.order.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

}
