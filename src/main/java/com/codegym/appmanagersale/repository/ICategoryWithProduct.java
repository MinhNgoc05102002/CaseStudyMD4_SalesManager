package com.codegym.appmanagersale.repository;

import com.codegym.appmanagersale.model.CategoryWithProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryWithProduct extends JpaRepository<CategoryWithProduct, Long> {
    List<CategoryWithProduct> findAllByProductId(Long id);
}
