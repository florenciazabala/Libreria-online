package com.egg.library.perisitence.mapper;

import com.egg.library.domain.UserVO;
import com.egg.library.perisitence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserVO toUser(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(UserVO userVO);
}
