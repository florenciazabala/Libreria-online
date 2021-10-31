package com.egg.library.perisitence.repository;

import com.egg.library.domain.PictureVO;
import com.egg.library.domain.repository.PictureVORepository;
import com.egg.library.perisitence.DAO.FotoDAO;
import com.egg.library.perisitence.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FotoRepository implements PictureVORepository {

    @Autowired
    private FotoDAO fotoDAO;

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public void create(PictureVO pictureVO) {
        fotoDAO.save(pictureMapper.toFoto(pictureVO));
    }

    @Override
    public void update(PictureVO pictureVO) {
        fotoDAO.update(pictureVO.getName(),pictureVO.getMime(),pictureVO.getPath(),pictureVO.getDischarge(),pictureVO.getId());
    }

    @Override
    public Optional<PictureVO> getById(Integer id) {
        return fotoDAO.findById(id).map(foto -> pictureMapper.toPictureVo(foto));
    }

    @Override
    public Optional<PictureVO> getByPath(String path) {
        return fotoDAO.findByRuta(path).map(foto -> pictureMapper.toPictureVo(foto));
    }

}
