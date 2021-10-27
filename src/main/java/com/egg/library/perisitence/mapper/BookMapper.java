package com.egg.library.perisitence.mapper;

import com.egg.library.domain.BookVO;
import com.egg.library.domain.CustomerVO;
import com.egg.library.perisitence.entity.Libro;
import com.egg.library.perisitence.entity.Prestamo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper(componentModel = "spring",uses={AuthorMapper.class,EditorialMapper.class})
public interface BookMapper {
    @Mappings({
            @Mapping(source = "titulo", target="title"),
            @Mapping(source = "genero", target = "genre"),
            @Mapping(source = "anio", target = "year"),
            @Mapping(source = "ejemplares", target = "copy"),
            @Mapping(source = "ejemplaresPrestados", target = "loanedCopy"),
            @Mapping(source = "ejemplaresRestantes", target = "avaibleCopy"),
            @Mapping(source = "alta", target = "discharged"),
            @Mapping(source = "autor", target = "author"),
            @Mapping(source = "prestamos",target = "loans")
    })
    BookVO toBookVO(Libro libro);
    List<BookVO> toBookVO(List<Libro> libros);

    @InheritInverseConfiguration
    @Mapping(target = "prestamos", ignore = true)
    Libro toLibro (BookVO bookVO);
    List<Libro> toLibro (List<BookVO> bookVO);

    default Map<Integer,Integer> fromBook(List<Prestamo> prestamos) {
        Map<Integer, Integer> loans = new HashMap<>();
        for (Prestamo prestamo : prestamos){
            loans.put(prestamo.getId(),prestamo.getCliente().getId());
        }
        return loans;
    }
}
