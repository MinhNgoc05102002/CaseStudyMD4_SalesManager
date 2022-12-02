package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Account;
import com.codegym.appmanagersale.model.Product;
import com.codegym.appmanagersale.repository.ICategoryWithProduct;
import com.codegym.appmanagersale.service.account.IAccountService;
import com.codegym.appmanagersale.service.category.ICategoryService;
import com.codegym.appmanagersale.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ICategoryWithProduct categoryWithProduct;

    @GetMapping("")
    public ModelAndView showProduct() {
        ModelAndView modelAndView = new ModelAndView("/admin/product/list");
        List<Product> products = (List<Product>) productService.findAll();
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("categories", categoryService.findAll());
        Account account = getUserCurrent();
        modelAndView.addObject("account", account);
        for (Product product : products) {
            setCategoryForProduct(product);
        }

        return modelAndView;
    }

    private void setCategoryForProduct(Product product) {
        product.setCategoryWithProducts((categoryWithProduct.findAllByProductId(product.getId())));
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/admin/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirect) {
        try {
            if (productService.save(product)) {
                redirect.addFlashAttribute("message", "Create product successfully!");
            } else {
                redirect.addFlashAttribute("message", "Create product failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("message", "Product already exists");
        }
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/admin/product/edit");
        modelAndView.addObject("product", productService.findById((long) id));
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(Product product, RedirectAttributes redirect) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (productService.save(product)) {
                redirect.addFlashAttribute("message", "Modified Product successfully!");
            } else {
                redirect.addFlashAttribute("message", "Modified product failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("message", "Product name already exists");
        }
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    private String deleteCategory(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            if (productService.remove(id)) {
                redirect.addFlashAttribute("message", "Removed product successfully!");
            } else {
                redirect.addFlashAttribute("message", "Removed product failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("message", "Product is being used or does not exist");
        }
        return "redirect:/products";
    }

    @PostMapping("/edit")
    public ModelAndView editProduct(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView("/admin/product/edit");
        try {
            if (productService.save(product)) {
                modelAndView.addObject("message", "Modified product successfully!");
            } else {
                modelAndView.addObject("message", "Modified product failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("message", "Product name already exists");
        }
        modelAndView.addObject("product", product);
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    public Account getUserCurrent() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return accountService.findByUsername(username);
    }
}
