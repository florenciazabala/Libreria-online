package com.egg.library.perisitence.repository;

import com.egg.library.domain.EditorialVO;
import com.egg.library.domain.repository.EditorialVORepository;
import com.egg.library.perisitence.DAO.EditorialDAO;
import com.egg.library.perisitence.entity.Editorial;
import com.egg.library.perisitence.mapper.EditorialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EditorialRepository implements EditorialVORepository{

    @Autowired
    private EditorialDAO editorialDAO;

    @Autowired
    private EditorialMapper editorialMapper;

    @Override
    public void create(EditorialVO editorialVO) {
        editorialDAO.save(editorialMapper.toEditorial(editorialVO));
    }

    @Override
    public List<EditorialVO> getAll() {
        return  editorialMapper.toEditorialVO(editorialDAO.findAll());
    }

    @Override
    public Optional<EditorialVO> getById(Integer id) {

        return  editorialDAO.findById(id).map(editorial -> editorialMapper.toEditorialVO(editorial));
    }

    @Override
    public void update(EditorialVO editorialVO) {
        Editorial editorial = editorialMapper.toEditorial(editorialVO);
        editorialDAO.update(editorial.getNombre(),editorial.getAlta(),editorial.getId());
    }

    @Override
    public EditorialVO getByName(String name) {
        return editorialMapper.toEditorialVO(editorialDAO.findByName(name));
    }

    @Override
    public boolean existsByName(String name) {
        return  editorialDAO.findByName(name) != null;
    }
}
