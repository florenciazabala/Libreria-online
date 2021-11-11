package com.egg.library.perisitence.mapper;

import com.egg.library.domain.EditorialVO;
import com.egg.library.perisitence.entity.Editorial;
import com.egg.library.perisitence.entity.Libro;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    default Map<Long,String> fromBook(List<Libro> libros) {
        Map<Long,String> books = new HashMap<>();
        if(libros != null){
            for (Libro libro : libros){
                books.put(libro.getIsbn(),libro.getTitulo());
            }
        }
        return books;
    }
}
