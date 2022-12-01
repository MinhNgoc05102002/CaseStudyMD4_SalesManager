package com.codegym.appmanagersale.repository;

import com.codegym.appmanagersale.model.Cart;
import com.codegym.appmanagersale.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByAccountId(Long id);
}
