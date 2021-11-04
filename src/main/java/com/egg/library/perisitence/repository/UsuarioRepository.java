package com.egg.library.perisitence.repository;

import com.egg.library.domain.UserVO;
import com.egg.library.domain.repository.UserRepository;
import com.egg.library.perisitence.DAO.UsuarioDAO;
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
}
