package com.egg.library.domain.service;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.UserVO;
import com.egg.library.domain.repository.CustomerVORepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    @Autowired
    private CustomerVORepository customerVORepository;


    @Autowired
    CustomerVO customerVO;

    @Transactional(readOnly = true)
    public List<CustomerVO> findAllCustomers(){
        return customerVORepository.getAllCustomers();
    }
    @Transactional(readOnly = true)
    public CustomerVO findBId(Integer id){
        return customerVORepository.getById(id)
                .orElseThrow(()->new NoSuchElementException("The customer with id '"+id+"' doesn't exists"));
    }

    @Transactional(readOnly = true)
    public CustomerVO findByUserMail(String mail){
        return customerVORepository.getByUserMail(mail)
                .orElseThrow(()->new NoSuchElementException("The customer with mail '"+mail+"' doesn't exists"));
    }

    private final Boolean DISCHARGED = Boolean.TRUE;

    @Transactional
    public void create(Long document,String name,String lastName,String mail,String telephone,UserVO user){
        if(customerVORepository.getByDocument(document).isPresent()){
            throw new FieldAlreadyExistException("The client with document'"+document+"' already exists");
        }
        setDates(document,name,lastName,mail,telephone,user);
        customerVORepository.createCustomer(customerVO);
    }

    @Transactional
    public void update(Integer id,Long document,String name,String lastName,String mail,String telephone){
        customerVO = customerVORepository.getById(id)
                .orElseThrow(()-> new NoSuchElementException("The client with id '"+id+"' doesn't exists"));

        if(customerVORepository.getByDocument(document).isPresent() && customerVORepository.getByDocument(document).get().getId() != id){
            throw new FieldAlreadyExistException("The client with document'"+document+"' already exists");
        }
        setDates(document,name,lastName,mail,telephone,customerVO.getUser());
        customerVORepository.updateCustomer(customerVO);
    }

    public void setDates(Long document,String name,String lastName,String mail,String telephone,UserVO user){

        Validations.validDocument(document);
        Validations.validString(name);
        Validations.validString(lastName);
        Validations.validString(mail);

        customerVO.setDocument(document);
        customerVO.setName(Validations.formatNames(name));
        customerVO.setLastName(Validations.formatNames(lastName));
        customerVO.setMail(mail);
        customerVO.setTelephone(telephone);
        customerVO.setDischarged(DISCHARGED);
        customerVO.setUser(user);
    }

    @Transactional
    public void delete(Integer id){
        customerVO = findBId(id);
        customerVO.setDischarged(!DISCHARGED);
        customerVORepository.updateCustomer(customerVO);
    }

    @Transactional
    public void discharged(Integer id){
        customerVO = findBId(id);
        customerVO.setDischarged(DISCHARGED);
        customerVORepository.updateCustomer(customerVO);
    }
}
