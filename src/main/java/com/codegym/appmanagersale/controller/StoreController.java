package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.*;
import com.codegym.appmanagersale.repository.ICategoryWithProduct;
import com.codegym.appmanagersale.repository.IOrderDetailRepository;
import com.codegym.appmanagersale.service.account.IAccountService;
import com.codegym.appmanagersale.service.cart.ICartService;
import com.codegym.appmanagersale.service.category.ICategoryService;
import com.codegym.appmanagersale.service.order.IOrderService;
import com.codegym.appmanagersale.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/store")
public class StoreController {

    private Account accountCurrent = null;
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

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

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
        Account account = getUserCurrent();
        modelAndView.addObject("account", account);
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
        accountCurrent = account;
        modelAndView.addObject("account", account);
        List<Cart> carts = cartService.findAllByAccountId(account.getId());
        long total = 0;
        for (Cart cart : carts) {
            total += cart.getQuantity() * cart.getProduct().getPriceOut();
        }
        modelAndView.addObject("total", total);
        modelAndView.addObject("carts", carts);
        return modelAndView;
    }

    @GetMapping("/show-history")
    public ModelAndView showHistory() {
        ModelAndView modelAndView = new ModelAndView("/user/ordered");
        Account account = getUserCurrent();
        modelAndView.addObject("account", account);
        accountCurrent = account;
        List<Order> orders = orderService.findAllByAccountId(account.getId());
        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(order.getId());
            long total = 0;
            for (OrderDetail orderDetail : orderDetails) {
                total += orderDetail.getQuantity() * orderDetail.getProduct().getPriceOut();
            }
            order.setTotal(total);
        }
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @PostMapping("/order")
    public String order(@RequestParam String orderAddress, RedirectAttributes redirect) {
        ModelAndView modelAndView = new ModelAndView();
        Account account = getUserCurrent();
        try {
            List<Cart> carts = cartService.findAllByAccountId(accountCurrent.getId());
            Order order = new Order();
            order.setAccount(accountCurrent);
            order.setAddress(orderAddress);
            orderService.save(order);
            OrderDetail orderDetail = new OrderDetail();
            for (Cart cart : carts) {
                orderDetail = new OrderDetail();
                orderDetail.setProduct(cart.getProduct());
                orderDetail.setQuantity(cart.getQuantity());
                orderDetail.setOrder(order);
                orderDetail.setPrice(cart.getProduct().getPriceOut());
                orderDetailRepository.save(orderDetail);
                cartService.remove(cart.getId());
                Product product = cart.getProduct();
                product.setQuantity(product.getQuantity() - cart.getQuantity());
                productService.save(product);
            }

            redirect.addFlashAttribute("message", "Order successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/store/show-cart";
    }

    @GetMapping("/delivered/{id}")
    public String delivered(@PathVariable Long id, RedirectAttributes redirect) {
        Optional<Order> order = orderService.findById(id);
        if (!order.get().getStatus().equals("CANCELLED")) {
            order.get().setStatus("DELIVERED");
            orderService.save(order.get());
        }
        return "redirect:/store/show-history";
    }

    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id, RedirectAttributes redirect) {
        Optional<Order> order = orderService.findById(id);
        order.get().setStatus("CANCELLED");
        orderService.save(order.get());
        return "redirect:/store/show-history";
    }

    public Account getUserCurrent() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return accountService.findByUsername(username);
    }
}
