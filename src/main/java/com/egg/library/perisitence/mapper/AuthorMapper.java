package com.egg.library.perisitence.mapper;

import com.egg.library.domain.AuthorVO;
import com.egg.library.perisitence.entity.Autor;
import com.egg.library.perisitence.entity.Libro;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring",uses={BookMapper.class,PictureMapper.class})
public interface AuthorMapper {
    @Mappings({
            @Mapping(source = "id",target = "idAuthor"),
            @Mapping(source ="nombre",target = "name"),
            @Mapping(source = "alta", target = "discharged"),
            @Mapping(source = "libros", target = "books"),
            @Mapping(source = "foto",target = "picture")
    })
    AuthorVO toAuthorVO(Autor autor);
    List<AuthorVO> toAuthorVO(List<Autor> autor);

    @InheritInverseConfiguration
    @Mapping(target="libros", ignore = true)
    Autor toAutor(AuthorVO authorVO);
    List<Autor> toAutor(List<AuthorVO> authorVO);


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
