package com.codegym.appmanagersale.repository;

import com.codegym.appmanagersale.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
