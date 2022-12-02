package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Account;
import com.codegym.appmanagersale.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/")
    public String showFormLogin() {
        return "/login";
    }

    @GetMapping("/login")
    public String reShowFormLogin() {
        return "/login";
    }

    @GetMapping("/register")
    public ModelAndView showFormRegister() {
        ModelAndView modelAndView = new ModelAndView("/register");
        modelAndView.addObject("account", new Account());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerAccount(@ModelAttribute("account") Account account) {
        ModelAndView modelAndView = new ModelAndView("/register");
        try {
            if (!accountService.save(account)) throw new Exception("Register failed");
            modelAndView.addObject("message", "success");
            modelAndView.setViewName("/login");
        } catch (Exception e) {
            modelAndView.addObject("message", "failed");
            modelAndView.addObject("account", new Account());
        }
        return modelAndView;
    }

    @GetMapping("/accounts")
    public ModelAndView showListAccount() {
        ModelAndView modelAndView = new ModelAndView("/admin/account/list");
        List<Account> accounts = (List<Account>) accountService.findAll();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getActive() == 0) {
                accounts.remove(i);
                i--;
            }
        }

        modelAndView.addObject("accounts", accounts);
        Account account = getUserCurrent();
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    @GetMapping("/accounts/{id}/delete")
    public String updateActiveAccount(@PathVariable Long id, RedirectAttributes redirect) {
        Account account = accountService.findById(id).get();
        account.setActive(0);
        if (accountService.save(account)) {
            redirect.addFlashAttribute("message", "Update status successfully!");
        } else {
            redirect.addFlashAttribute("message", "Update status failed!");
        }
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/{role}/update/{id}")
    public String updateRoleAccount(@PathVariable String role, @PathVariable Long id, RedirectAttributes redirect) {
        Account account = accountService.findById(id).get();
        Account accountCurrent = getUserCurrent();
        if (account.getId()  != accountCurrent.getId()) {
            if (role.equals("ROLE_ADMIN"))
                account.setUserRole("ROLE_USER");
            else
                account.setUserRole("ROLE_ADMIN");
        }
        if (accountService.save(account)) {
            redirect.addFlashAttribute("message", "Update role successfully!");
        } else {
            redirect.addFlashAttribute("message", "Update role failed!");
        }
        return "redirect:/accounts";
    }


    public Account getUserCurrent() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return accountService.findByUsername(username);
    }

    @GetMapping("/access-denied")
    public String errorPage(){
        return "/Access-Denied";
    }
}