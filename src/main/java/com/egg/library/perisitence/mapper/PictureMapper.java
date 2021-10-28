package com.egg.library.perisitence.mapper;

import com.egg.library.domain.PictureVO;
import com.egg.library.perisitence.entity.Foto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PictureMapper {
    @Mappings({
            @Mapping(source = "nombre",target = "name"),
            @Mapping(source = "ruta",target = "path"),
            @Mapping(source = "alta",target = "discharge")
    })
    PictureVO toPictureVo(Foto foto);

    @InheritInverseConfiguration
    Foto toFoto(PictureVO pictureVO);
}
