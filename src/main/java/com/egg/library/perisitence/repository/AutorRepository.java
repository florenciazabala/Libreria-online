package com.egg.library.perisitence.repository;

import com.egg.library.domain.AuthorVO;
import com.egg.library.domain.repository.AuthorVORepository;
import com.egg.library.perisitence.DAO.AutorDAO;
import com.egg.library.perisitence.entity.Autor;
import com.egg.library.perisitence.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AutorRepository implements AuthorVORepository {

    @Autowired
    private  AutorDAO autorDAO;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public AuthorVO create(AuthorVO authorVO) {
        return authorMapper.toAuthorVO(autorDAO.save(authorMapper.toAutor(authorVO)));
    }

    @Override
    public Page<AuthorVO> getAll(Pageable pageable) {
        Page<Autor> autores = autorDAO.findAll(pageable);
        List<AuthorVO> authors = authorMapper.toAuthorVO(autores.getContent());
        return new PageImpl<>(authors, pageable, autores.getTotalElements());
    }

    @Override
    public Optional<AuthorVO> getById(Integer id) {
        return autorDAO.findById(id).map(autor -> authorMapper.toAuthorVO(autor));
    }

    @Override
    public void update(AuthorVO authorVO) {
        Autor autor = authorMapper.toAutor(authorVO);
        autorDAO.update(autor.getNombre(),autor.getAlta(),autor.getId());
    }

    @Override
    public AuthorVO getByName(String name) {
        return authorMapper.toAuthorVO(autorDAO.findByName(name));
    }

    @Override
    public boolean existsByName(String name) {
        return autorDAO.findByName(name) != null;
    }
}
