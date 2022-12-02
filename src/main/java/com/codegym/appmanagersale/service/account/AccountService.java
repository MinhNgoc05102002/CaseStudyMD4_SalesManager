package com.codegym.appmanagersale.service.account;

import com.codegym.appmanagersale.model.Account;
import com.codegym.appmanagersale.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("userDetailsService")
@Transactional
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Boolean save(Account account) {
        if (account.getPassword().length() < 20) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        try {
            accountRepository.save(account);
            return true;
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            System.out.println("Error duplicate username or email when save user!");
        } catch (Exception e) {
            System.out.println("Error another when save user!");
        }
        return false;
    }

    @Override
    public Boolean remove(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new RuntimeException("User not found");
        }
        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(account.getUserRole()));
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), auth);
    }

    @Override
    public Account findByUsername(String username) {
        try {
            Account account = accountRepository.findByUsername(username);
            if (account == null) {
                throw new Exception("Account not found!");
            }
            return account;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
