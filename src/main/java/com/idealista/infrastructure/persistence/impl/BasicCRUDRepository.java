package com.idealista.infrastructure.persistence.impl;

import java.util.List;

public interface BasicCRUDRepository<T> {
    List<T> findAll();

    T findById(Integer id);

    T saveOrUpdate(T domainObject);
}
