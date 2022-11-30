package com.codegym.appmanagersale.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage(("/login"))
                .usernameParameter("username")
                .passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/store").failureUrl("/login?error");

        http.logout().logoutSuccessUrl("/login");

        http.exceptionHandling().accessDeniedPage("/access-denied");

        http.authorizeRequests().antMatchers("/login", "/logout", "/home", "/register", "/store", "/assets/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated();
        http.csrf().disable();
    }
}
// restcontroller là gì
// nó là 1 annotation của spring boot dùng để thay thế cho @controller và @responsebody để trả về json

// ioc nó có tác dụng là:
// 1. giúp chúng ta có thể sử dụng được các đối tượng mà chúng ta không cần phải khởi tạo
// 2. giúp chúng ta có thể sử dụng được các đối tượng mà chúng ta không cần phải quản lý
// 3. giúp chúng ta có thể sử dụng được các đối tượng mà chúng ta không cần phải khởi tạo và quản lý
// ioc nó hoạt động như thế nào:
// 1. chúng ta sẽ khai báo các đối tượng cần sử dụng trong file xml
// 2. chúng ta sẽ khai báo các đối tượng cần sử dụng trong file java

// dependency injection là gì:
// 1. là một kĩ thuật trong lập trình hướng đối tượng
// 2. là một kĩ thuật trong lập trình hướng đối tượng giúp chúng ta có thể sử dụng được các đối tượng mà chúng ta không cần phải khởi tạo và quản lý

