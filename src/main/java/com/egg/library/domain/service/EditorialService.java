package com.egg.library.domain.service;

import com.egg.library.domain.EditorialVO;
import com.egg.library.domain.repository.EditorialVORepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditorialService {

    @Autowired
    private EditorialVORepository editorialVORepository;

    @Transactional(readOnly = true)
    public List<EditorialVO> findAllEditorials(){
        return editorialVORepository.getAll();
    }

    private final Boolean DISCHARGED = Boolean.TRUE;
    @Transactional
    public void createEditorial(String name){
        if(editorialVORepository.existsByName(name)){
            throw new FieldAlreadyExistException("The editorial with name '"+name+"' already exists");
        }
        Validations.validString(name);
        EditorialVO editorialVO= new EditorialVO();
        editorialVO.setName(Validations.formatNames(name));
        editorialVO.setDischarged(DISCHARGED);
        editorialVORepository.create(editorialVO);
    }

    @Transactional
    public void updateEditorial(String name,Integer id){
        EditorialVO editorialVO= editorialVORepository.getById(id)
                .orElseThrow(()-> new FieldAlreadyExistException("The editorial with id '"+id+"' doesn't exists"));
        Validations.validString(name);
        editorialVO.setName(Validations.formatNames(name));
        editorialVO.setDischarged(DISCHARGED);
        editorialVORepository.update(editorialVO);
    }

    @Transactional
    public void delete(Integer id){
        EditorialVO editorialVO= editorialVORepository.getById(id)
                .orElseThrow(()-> new FieldAlreadyExistException("The editorial with id '"+id+"' doesn't exists"));
        editorialVO.setDischarged(!DISCHARGED);
        editorialVORepository.update(editorialVO);
    }

    @Transactional
    public EditorialVO findByName(String name){
        return editorialVORepository.getByName(name);
    }
}
