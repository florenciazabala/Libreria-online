package com.egg.library.domain.repository;

import com.egg.library.domain.UserVO;

import java.util.Optional;


public interface UserRepository {
    UserVO create(UserVO userVO);
    void update(UserVO userVO);
    Optional<UserVO> findByMailOrUsername(String mail, String username);
    Optional<UserVO> findByMail(String mail);
    Optional<UserVO> findByUsername(String username);
    Optional<UserVO> findById(Integer id);
    void delete(Integer id);
    void discharge (Integer id);

    void deleteRelationRolUser(Integer idUser,Integer idRol);
    void createRelation(Integer idUser,Integer idRol);
    Boolean existsRelation(Integer idUser,Integer idRol);
}
