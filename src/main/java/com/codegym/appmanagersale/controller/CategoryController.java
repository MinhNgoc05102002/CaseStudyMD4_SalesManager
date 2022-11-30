package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping()
    private ModelAndView showCategory() {
        ModelAndView modelAndView = new ModelAndView("/admin/category/list");
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }
}
