package com.egg.library.perisitence.repository;

import com.egg.library.domain.AuthorVO;
import com.egg.library.domain.PictureVO;
import com.egg.library.domain.repository.AuthorVORepository;
import com.egg.library.perisitence.DAO.AutorDAO;
import com.egg.library.perisitence.entity.Autor;
import com.egg.library.perisitence.entity.Foto;
import com.egg.library.perisitence.mapper.AuthorMapper;
import com.egg.library.perisitence.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AutorRepository implements AuthorVORepository {

    @Autowired
    private  AutorDAO autorDAO;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public void create(AuthorVO authorVO) {
        autorDAO.save(authorMapper.toAutor(authorVO));
    }


    @Override
    public List<AuthorVO> getAll() {
        return authorMapper.toAuthorVO(autorDAO.findAll());
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
    public void updateFoto(PictureVO pictureVO, Integer id) {
        Foto foto = pictureMapper.toFoto(pictureVO);
        autorDAO.updateFoto(foto,id);
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
