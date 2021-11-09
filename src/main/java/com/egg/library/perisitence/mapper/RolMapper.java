package com.egg.library.perisitence.mapper;

import com.egg.library.domain.RolVO;
import com.egg.library.perisitence.entity.Rol;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(source = "rol", target = "role")
    RolVO toRolVO(Rol rol);
    List<RolVO> toRolVO(List<Rol> rol);

    @InheritInverseConfiguration
    Rol toRol(RolVO rolVO);
    List<Rol> toRol(List<RolVO> rolVO);
}
