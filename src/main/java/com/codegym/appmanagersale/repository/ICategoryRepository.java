package com.codegym.appmanagersale.repository;

import com.codegym.appmanagersale.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
