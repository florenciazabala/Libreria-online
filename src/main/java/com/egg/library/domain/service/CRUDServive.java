package com.egg.library.domain.service;

import java.util.List;

public interface CRUDServive <E,K>{
    public void create(E e);
    public List<E> findAll();
    public E fondByName(String name);
}
