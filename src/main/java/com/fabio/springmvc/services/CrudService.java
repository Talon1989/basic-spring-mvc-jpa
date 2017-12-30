package com.fabio.springmvc.services;

import java.util.List;

public interface CrudService <T>{
    List<?> listAll();
    T getById(Integer id);
    T saveOrUpdate(T domainObject);
    void delete(Integer id);
}
