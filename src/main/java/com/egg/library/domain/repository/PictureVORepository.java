package com.egg.library.domain.repository;

import com.egg.library.domain.PictureVO;

import java.util.Optional;

public interface PictureVORepository {
    void create(PictureVO pictureVO);
    void update(PictureVO pictureVO);
    Optional<PictureVO> getById(Integer id);
}
