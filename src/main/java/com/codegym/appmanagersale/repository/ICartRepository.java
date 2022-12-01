package com.codegym.appmanagersale.repository;

import com.codegym.appmanagersale.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {
}
