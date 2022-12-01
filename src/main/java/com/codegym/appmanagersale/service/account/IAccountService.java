package com.codegym.appmanagersale.service.account;

import com.codegym.appmanagersale.model.Account;
import com.codegym.appmanagersale.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountService extends UserDetailsService, IGeneralService<Account> {
    Account findByUsername(String username);
}
