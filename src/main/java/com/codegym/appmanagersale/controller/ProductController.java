package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Product;
import com.codegym.appmanagersale.repository.ICategoryWithProduct;
import com.codegym.appmanagersale.service.account.IAccountService;
import com.codegym.appmanagersale.service.category.ICategoryService;
import com.codegym.appmanagersale.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        ModelAndView modelAndView = new ModelAndView("/admin/product/list");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/admin/product/edit");
        modelAndView.addObject("product", productService.findById(id));
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
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

    @GetMapping("/delete/{id}")
    private String deleteProduct(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            if (productService.remove(id)) {
                redirect.addFlashAttribute("message", "Delete product successfully!");
            } else {
                redirect.addFlashAttribute("message", "Delete product failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("message", "Product name already exists");
        }
        return "redirect:/products";
    }
}
