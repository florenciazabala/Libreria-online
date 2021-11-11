package com.egg.library.perisitence.repository;

import com.egg.library.domain.UserVO;
import com.egg.library.domain.repository.UserRepository;
import com.egg.library.perisitence.DAO.UsuarioDAO;
import com.egg.library.perisitence.mapper.RolMapper;
import com.egg.library.perisitence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolMapper rolMapper;

    @Override
    public UserVO create(UserVO userVO) {
        return userMapper.toUser(usuarioDAO.save(userMapper.toUsuario(userVO)));
    }

    @Override
    public void update(UserVO userVO) {
        usuarioDAO.update(userVO.getUsername(),userVO.getMail(),userVO.getPassword(),userVO.getId());
    }

    @Override
    public Optional<UserVO> findByMailOrUsername(String mail, String username) {
        return usuarioDAO.findByMailOrUsername(mail,username).map(usuario -> userMapper.toUser(usuario));
    }

    @Override
    public Optional<UserVO> findByMail(String mail) {
        return usuarioDAO.findByMail(mail).map(usuario -> userMapper.toUser(usuario));
    }

    @Override
    public Optional<UserVO> findByUsername(String username) {
        return usuarioDAO.findByUsername(username).map(usuario -> userMapper.toUser(usuario));
    }

    @Override
    public Optional<UserVO> findById(Integer id) {
        return usuarioDAO.findById(id).map(usuario -> userMapper.toUser(usuario));
    }

    @Override
    public void delete(Integer id) {
        usuarioDAO.deleteById(id);
    }

    @Override
    public void discharge(Integer id) {
        usuarioDAO.updateAlta(id);
    }

    @Override
    public void deleteRelationRolUser(Integer idUser, Integer idRol) {
        usuarioDAO.deleteRelationUserRol(idUser,idRol);
    }

    @Override
    public void createRelation(Integer idUser, Integer idRol) {
        usuarioDAO.saveRelation(idUser,idRol);
    }

    @Override
    public Boolean existsRelation(Integer idUser, Integer idRol) {
        if(usuarioDAO.existsRelation(idUser,idRol)==0){
            return false;
        }
        return true;
    }
}
