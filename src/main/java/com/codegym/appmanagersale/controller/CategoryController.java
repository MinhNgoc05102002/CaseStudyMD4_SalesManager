package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Category;
import com.codegym.appmanagersale.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    private ModelAndView showCategory() {
        ModelAndView modelAndView = new ModelAndView("/admin/category/list");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    private ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/admin/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    private ModelAndView createCategory(Category category) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            categoryService.save(category);
            modelAndView.addObject("message", "New category created successfully");
            return new ModelAndView("redirect:/categories");
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("message", "New category created failed");
            return new ModelAndView("/admin/category/list");
        }
    }
}
