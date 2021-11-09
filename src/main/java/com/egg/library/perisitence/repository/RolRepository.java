package com.egg.library.perisitence.repository;

import com.egg.library.domain.RolVO;
import com.egg.library.domain.repository.RolVORepository;
import com.egg.library.perisitence.DAO.RolDAO;
import com.egg.library.perisitence.mapper.RolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolRepository implements RolVORepository {

    @Autowired
    private RolDAO rolDAO;

    @Autowired
    private RolMapper rolMapper;

    @Override
    public RolVO create(RolVO rolVO) {
        return rolMapper.toRolVO(rolDAO.save(rolMapper.toRol(rolVO)));
    }

    @Override
    public Optional<RolVO> getById(Integer id) {
        return rolDAO.findById(id).map(rol -> rolMapper.toRolVO(rol));
    }

    @Override
    public Optional<RolVO> getByRol(String rol) {
        return rolDAO.findByRol(rol).map(r -> rolMapper.toRolVO(r));
    }

    @Override
    public List<RolVO> getAll() {
        return rolMapper.toRolVO(rolDAO.findAll());
    }
}
