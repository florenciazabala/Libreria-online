package com.egg.library.domain.repository;

import com.egg.library.domain.AuthorVO;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;


public interface AuthorVORepository {

    void create(AuthorVO authorVO);
    List<AuthorVO> getAll();
    Optional<AuthorVO> getById(Integer id);
    void update(AuthorVO authorVO);
    AuthorVO getByName(String name);
    boolean existsByName(String name);

}
