package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Account;
import com.codegym.appmanagersale.model.Category;
import com.codegym.appmanagersale.service.account.IAccountService;
import com.codegym.appmanagersale.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IAccountService accountService;

    @GetMapping("")
    private ModelAndView showCategory() {
        ModelAndView modelAndView = new ModelAndView("/admin/category/list");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("categories", categoryService.findAll());
        Account account = getUserCurrent();
        modelAndView.addObject("account", account);
        return modelAndView;
    }


    @PostMapping("/create")
    private String createCategory(Category category, RedirectAttributes redirect) {
        try {
            if (categoryService.save(category)) {
                redirect.addFlashAttribute("message", "Create category successfully!");
            } else {
                redirect.addFlashAttribute("message", "Create category failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("message", "Category name already exists");
        }
        return "redirect:/categories";

    }

    @PostMapping("/edit/{id}")
    private String editCategory(@PathVariable Long id, @ModelAttribute("category") Category category, RedirectAttributes redirect) {
        category.setId(id);
        try {
            if (categoryService.save(category)) {
                redirect.addFlashAttribute("message", "Modified category successfully!");
            } else {
                redirect.addFlashAttribute("message", "Modified category failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("message", "Category name already exists");
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    private String deleteCategory(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            if (categoryService.remove(id)) {
                redirect.addFlashAttribute("message", "Removed category successfully!");
            } else {
                redirect.addFlashAttribute("message", "Removed category failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("message", "Category is being used or does not exist");
        }
        return "redirect:/categories";
    }
    public Account getUserCurrent() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return accountService.findByUsername(username);
    }
}
