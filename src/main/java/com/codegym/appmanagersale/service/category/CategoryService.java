package com.codegym.appmanagersale.service.category;

import com.codegym.appmanagersale.model.Category;
import com.codegym.appmanagersale.model.CategoryWithProduct;
import com.codegym.appmanagersale.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        try {
            Category category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                throw new Exception("Category not found!");
            }
            return Optional.of(category); // Optional.ofNullable(category);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Boolean save(Category category) {
        try {
            if (category == null) throw new Exception("Category is null!");
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean remove(Long id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}