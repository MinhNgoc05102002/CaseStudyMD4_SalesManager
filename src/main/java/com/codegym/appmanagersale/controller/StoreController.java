package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Account;
import com.codegym.appmanagersale.model.Cart;
import com.codegym.appmanagersale.model.Product;
import com.codegym.appmanagersale.repository.ICategoryWithProduct;
import com.codegym.appmanagersale.service.account.IAccountService;
import com.codegym.appmanagersale.service.cart.ICartService;
import com.codegym.appmanagersale.service.category.ICategoryService;
import com.codegym.appmanagersale.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICategoryWithProduct categoryWithProduct;

    @Autowired
    private ICartService cartService;

    @GetMapping("")
    public ModelAndView showStore() {
        ModelAndView modelAndView = new ModelAndView("/user/store");
        modelAndView.addObject("products", productService.findAll());
        Account account = getUserCurrent();
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    @GetMapping("/showAllProducts")
    public ModelAndView showAllProducts() {
        ModelAndView modelAndView = new ModelAndView("/user/productByCategory");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("products", productService.findAll());
        Account account = getUserCurrent();
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    @GetMapping("/productDetail/{id}")
    public ModelAndView showProductDetail(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/user/productDetail");
        modelAndView.addObject("product", productService.findById((long) id).get());
        modelAndView.addObject("categories", categoryWithProduct.findAllByProductId((long) id));
        return modelAndView;
    }

    @PostMapping("/cart/{id}")
    public ModelAndView addToCart(@RequestParam Integer quantity, @PathVariable Long id) {
        try {
            Product product = productService.findById(id).get();
            Account account = getUserCurrent();
            Cart cart = new Cart();
            cart.setAccount(account);
            cart.setQuantity(quantity);
            cart.setProduct(product);
            cartService.save(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/store/productDetail/" + id);
    }

    @GetMapping("/show-cart")
    public ModelAndView showCart() {
        ModelAndView modelAndView = new ModelAndView("/user/cart");
        Account account = getUserCurrent();
        List<Cart> carts = cartService.findAllByAccountId(account.getId());
        modelAndView.addObject("carts", carts);
        return modelAndView;
    }

    public Account getUserCurrent() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return accountService.findByUsername(username);
    }
}
