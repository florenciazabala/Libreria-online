package com.egg.library.domain.service;

import com.egg.library.domain.RolVO;
import com.egg.library.domain.repository.RolVORepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.exeptions.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolVORepository rolVORepository;

    @Autowired
    private RolVO rolVO;

    @Transactional
    public RolVO create(String role){
        if(rolVORepository.getByRol(role).isPresent()){
            throw new FieldAlreadyExistException ("The role '"+role+"' already exists");
        }
        rolVO.setRole(role);
        return rolVORepository.create(rolVO);
    }

    @Transactional(readOnly = true)
    public RolVO findById(Integer id){
        return rolVORepository.getById(id).orElseThrow(() ->new NoSuchElementException("The rol with id '"+id+"' doesn't exists"));
    }

    @Transactional(readOnly = true)
    public RolVO findByRole(String role){
        return rolVORepository.getByRol(role).orElseThrow(() ->new NoSuchElementException("The rol with role '"+role+"' doesn't exists"));
    }

    @Transactional(readOnly = true)
    public List<RolVO> findAll(){
        return rolVORepository.getAll();
    }

}
