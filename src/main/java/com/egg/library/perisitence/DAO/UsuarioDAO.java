package com.egg.library.perisitence.DAO;

import com.egg.library.perisitence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UsuarioDAO extends JpaRepository<Usuario,Integer> {

    @Modifying
    @Query("UPDATE Usuario u SET u.username = :username, u.mail = :mail, u.mail = :mail, u.password = :password WHERE u.id = :id")
    void update(@Param("username")String username, @Param("mail")String mail, @Param("password")String password,@Param("id") Integer id);

    @Modifying
    @Query("UPDATE Usuario u SET u.alta = true WHERE u.id = :id")
    void updateAlta(@Param("id") Integer id);

    Optional<Usuario> findByMailOrUsername(String mail, String username);
    Optional<Usuario> findByMail(String mail);
    Optional<Usuario> findByUsername(String username);

    /*Relations*/
    @Modifying
    @Query( value = "DELETE FROM usuarios_roles WHERE usuario_id= :idUser AND roles_id = :idRol", nativeQuery = true)
    void deleteRelationUserRol (@Param("idUser") Integer idUser,@Param("idRol") Integer idRol);
    @Query( value = "SELECT count(*) FROM  usuarios_roles WHERE EXISTS( SELECT * FROM  usuarios_roles  WHERE usuario_id= :idUser AND roles_id = :idRol) LIMIT 1", nativeQuery = true)
    Integer existsRelation(@Param("idUser") Integer idUser,@Param("idRol")Integer idRol);
    @Modifying
    @Query( value = "INSERT INTO usuarios_roles  (usuario_id, roles_id) values (:idUser ,:idRol)", nativeQuery = true)
    void saveRelation(@Param("idUser")Integer idUser,@Param("idRol")Integer idRol);
}
