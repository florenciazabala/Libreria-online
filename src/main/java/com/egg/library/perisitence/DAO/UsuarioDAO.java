package com.egg.library.perisitence.DAO;

import com.egg.library.perisitence.entity.Rol;
import com.egg.library.perisitence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO extends JpaRepository<Usuario,Integer> {

    @Modifying
    @Query("UPDATE Usuario u SET u.username = :username, u.mail = :mail, u.mail = :mail, u.password = :password, u.roles = :roles WHERE u.id = :id")
    public void update(@Param("username")String username, @Param("mail")String mail, @Param("password")String password,
                       @Param("roles") List<Rol> roles, @Param("id")Integer id);

    Optional<Usuario> findByMailOrUsername(String mail, String username);
    Optional<Usuario> findByMail(String mail);
    Optional<Usuario> findByUsername(String username);
}
