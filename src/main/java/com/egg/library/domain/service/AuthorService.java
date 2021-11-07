package com.egg.library.domain.service;

import com.egg.library.domain.AuthorVO;
import com.egg.library.domain.repository.AuthorVORepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorService {

    @Autowired
    private AuthorVORepository authorVORepository;

    @Autowired
    private AuthorVO authorVO;


    private final Boolean DISCHARGED = Boolean.TRUE;

    @Transactional
    public void createAuthor(String name){
        if(authorVORepository.existsByName(name)){
            throw new FieldAlreadyExistException("The author with name '"+name+"' already exists");
        }

        authorVO = new AuthorVO();
        Validations.validString(name);
        authorVO.setName(Validations.formatNames(name));
        authorVO.setDischarged(DISCHARGED);
        authorVORepository.create(authorVO);
    }

    @Transactional
    public void updateAuthor(String name, Integer id){
        authorVO = authorVORepository.getById(id)
                .orElseThrow(()-> new FieldAlreadyExistException("The author with id '"+id+"' doesn't exists"));

        if(authorVORepository.getByName(name) != null && authorVORepository.getByName(name).getIdAuthor() != id){
            throw new FieldAlreadyExistException("The author with name '"+name+"' already exists");
        }

        Validations.validString(name);
        authorVO.setName(Validations.formatNames(name));
        authorVO.setDischarged(DISCHARGED);
        authorVORepository.update(authorVO);
    }

    @Transactional(readOnly = true)
    public Page<AuthorVO> findAll(Pageable pageable){
        return authorVORepository.getAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<AuthorVO> findAll(){
        return authorVORepository.getAll();
    }

    @Transactional(readOnly = true)
    public AuthorVO findByName(String name){
        return authorVORepository.getByName(name);
    }

    @Transactional(readOnly = true)
    public AuthorVO findById(Integer id){
        return authorVORepository.getById(id)
                .orElseThrow(() ->new NoSuchElementException("The author with id '"+id+"' doesn't exists"));
    }

    @Transactional
    public void delete(Integer id){
        AuthorVO authorVO = authorVORepository.getById(id)
                .orElseThrow(()-> new FieldAlreadyExistException("The author with id '"+id+"' doesn't exists"));

        authorVO.setDischarged(!DISCHARGED);
        authorVORepository.update(authorVO);
    }

    @Transactional
    public void discharge(Integer id){
        AuthorVO authorVO = authorVORepository.getById(id)
                .orElseThrow(()-> new FieldAlreadyExistException("The author with id '"+id+"' doesn't exists"));

        authorVO.setDischarged(DISCHARGED);
        authorVORepository.update(authorVO);
    }
}
