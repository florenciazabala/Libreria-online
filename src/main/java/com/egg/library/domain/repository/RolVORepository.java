package com.egg.library.domain.repository;

import com.egg.library.domain.RolVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RolVORepository {
    RolVO create(RolVO rolVO);
    Optional<RolVO> getById(Integer id);
    Optional<RolVO> getByRol(String rol);
    List<RolVO> getAll();

}
