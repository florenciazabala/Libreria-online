package com.egg.library.perisitence.mapper;

import com.egg.library.domain.EditorialVO;
import com.egg.library.perisitence.entity.Editorial;
import com.egg.library.perisitence.entity.Libro;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses={BookMapper.class})
public interface EditorialMapper {

    @Mappings({
            @Mapping(source ="nombre",target = "name"),
            @Mapping(source = "alta", target = "discharged"),
            @Mapping(source = "libros", target = "books")
    })
    EditorialVO toEditorialVO(Editorial editorial);
    List<EditorialVO> toEditorialVO(List<Editorial> editorial);

    @InheritInverseConfiguration
    @Mapping(target="libros",ignore = true)
    Editorial toEditorial(EditorialVO editorialVO);
    List<Editorial> toEditorial(List<EditorialVO> editorialVO);

    default String fromBook(Libro libro) {
        return libro == null ? null : libro.getTitulo();
    }
}
