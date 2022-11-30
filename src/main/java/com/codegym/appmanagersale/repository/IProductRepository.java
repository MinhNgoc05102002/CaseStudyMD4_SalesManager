package com.codegym.appmanagersale.repository;

import com.codegym.appmanagersale.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
