package com.tdtu.ecommerce.service;

import java.util.List;

public interface ProductService <T>{
    List<T> getAll();

    void save(T t);

    void saveAll(List<T> ts);

    void delete(Long id);
}
