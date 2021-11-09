package com.egg.library.perisitence.mapper;

import com.egg.library.domain.UserVO;
import com.egg.library.perisitence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
<<<<<<< HEAD



@Mapper(componentModel = "spring",uses={RolMapper.class})
=======



@Mapper(componentModel = "spring")
>>>>>>> 3176da758fc11dba302f5dd86e01d2222f8a1478
public interface UserMapper {
    @Mapping(source = "alta",target = "discharged")
    UserVO toUser(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(UserVO userVO);
}
