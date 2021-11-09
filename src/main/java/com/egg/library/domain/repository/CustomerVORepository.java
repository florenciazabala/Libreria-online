package com.egg.library.domain.repository;

import com.egg.library.domain.CustomerVO;

import java.util.List;
import java.util.Optional;


public interface CustomerVORepository {
    CustomerVO createCustomer(CustomerVO customerVO);
    void updateCustomer(CustomerVO customerVO);
    Optional<CustomerVO> getById(Integer id);
    Optional<CustomerVO> getByDocument(Long document);
    Optional<CustomerVO> getByUserMail(String mail);
    List<CustomerVO> getAllCustomers();
}
