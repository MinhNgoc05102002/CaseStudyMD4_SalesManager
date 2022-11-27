package com.codegym.appmanagersale.repository;

import com.codegym.appmanagersale.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
