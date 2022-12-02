package com.codegym.appmanagersale.repository;

import com.codegym.appmanagersale.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
//    Order findByUserId(Long userId);
}
