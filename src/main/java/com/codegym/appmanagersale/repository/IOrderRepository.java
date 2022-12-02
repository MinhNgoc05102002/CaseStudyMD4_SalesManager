package com.codegym.appmanagersale.repository;

import com.codegym.appmanagersale.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByAccountId(Long id);
}
