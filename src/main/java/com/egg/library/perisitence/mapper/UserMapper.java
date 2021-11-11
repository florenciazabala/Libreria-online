package com.egg.library.perisitence.mapper;

import com.egg.library.domain.UserVO;
import com.egg.library.perisitence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring",uses={RolMapper.class})
public interface UserMapper {
    @Mapping(source = "alta",target = "discharged")
    UserVO toUser(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(UserVO userVO);
}
