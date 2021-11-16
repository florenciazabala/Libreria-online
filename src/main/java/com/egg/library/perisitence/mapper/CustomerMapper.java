package com.egg.library.perisitence.mapper;

import com.egg.library.domain.CustomerVO;
import com.egg.library.perisitence.entity.Cliente;
import com.egg.library.perisitence.entity.Libro;
import com.egg.library.perisitence.entity.Prestamo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring",uses={UserMapper.class,LoanMapper.class,PictureMapper.class})
public interface CustomerMapper {
    @Mappings({
            @Mapping(source = "documento",target = "document"),
            @Mapping(source = "nombre",target = "name"),
            @Mapping(source = "apellido",target = "lastName"),
            @Mapping(source = "telefono",target = "telephone"),
            @Mapping(source = "alta",target = "discharged"),
            @Mapping(source = "prestamos",target = "loans"),
            @Mapping(source = "usuario",target = "user"),
            @Mapping(source = "foto",target = "picture")
    })
    CustomerVO toCustomerVO(Cliente cliente);
    List<CustomerVO> toCustomerVO(List<Cliente> cliente);

    @InheritInverseConfiguration
    @Mapping(target = "prestamos",ignore = true)
    Cliente toCliente(CustomerVO customerVO);
    List<Cliente> toCliente(List<CustomerVO> customerVO);

    default Map<Integer,String> fromBook(List<Prestamo> prestamos) {
        Map<Integer,String> loans = new HashMap<>();
        if(prestamos != null){
            for (Prestamo prestamo : prestamos){
                loans.put(prestamo.getId(),prestamo.getLibro().getTitulo());
            }
        }
        return loans;
    }

}
