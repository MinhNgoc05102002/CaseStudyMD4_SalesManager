package com.codegym.appmanagersale.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    Boolean save(T t);

    Boolean remove(Long id);
}
