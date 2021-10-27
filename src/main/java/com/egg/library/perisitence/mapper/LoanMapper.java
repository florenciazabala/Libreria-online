package com.egg.library.perisitence.mapper;

import com.egg.library.domain.LoanVO;
import com.egg.library.perisitence.entity.Prestamo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses={BookMapper.class,CustomerMapper.class})
public interface LoanMapper {
    @Mappings({
            @Mapping(source ="fechaPrestamo",target = "loanDate"),
            @Mapping(source = "fechaDevolucion",target = "returnDate"),
            @Mapping(source = "alta",target = "discharged"),
            @Mapping(source = "libro",target = "book"),
            @Mapping(source = "cliente",target = "customer")
    })
    LoanVO toLoanVO(Prestamo prestamo);
    List<LoanVO> toLoanVO(List<Prestamo> prestamo);

    @InheritInverseConfiguration
    Prestamo toPrestamo(LoanVO loanVO);
    List<Prestamo> toPrestamo(List<LoanVO> loanVO);
}
