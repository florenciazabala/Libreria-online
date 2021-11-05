package com.egg.library.domain.repository;

import com.egg.library.domain.EditorialVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public interface EditorialVORepository {

    EditorialVO create(EditorialVO editorialVO);
    List<EditorialVO> getAll();
    Optional<EditorialVO> getById(Integer id);
    void update(EditorialVO editorialVO);
    EditorialVO getByName(String name);
    boolean existsByName(String name);

}
