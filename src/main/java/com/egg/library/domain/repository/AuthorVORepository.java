package com.egg.library.domain.repository;

import com.egg.library.domain.AuthorVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;


public interface AuthorVORepository {

    AuthorVO create(AuthorVO authorVO);
    Page<AuthorVO> getAll(Pageable pageable);
    List<AuthorVO> getAll();
    Optional<AuthorVO> getById(Integer id);
    void update(AuthorVO authorVO);
    AuthorVO getByName(String name);
    boolean existsByName(String name);

}
