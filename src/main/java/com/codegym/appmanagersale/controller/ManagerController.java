package com.codegym.appmanagersale.controller;

import com.codegym.appmanagersale.model.Account;
import com.codegym.appmanagersale.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class ManagerController {
    @Autowired
    private IAccountService accountService;

    @RequestMapping("")
    public ModelAndView showProduct() {
        ModelAndView modelAndView = new ModelAndView("/admin/manager");
        Account account = getUserCurrent();
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    public Account getUserCurrent() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return accountService.findByUsername(username);
    }
}
