package com.egg.library.perisitence.DAO;

import com.egg.library.perisitence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO extends JpaRepository<Cliente,Integer> {

    @Modifying
    @Query("UPDATE Cliente c SET c.documento = :documento, c.nombre = :nombre, c.apellido = :apellido, c.mail= :mail, c.telefono = :telefono," +
            " c.alta = :alta WHERE c.id = :id")
    void update(@Param("documento")Long documento,@Param("nombre")String nombre,@Param("apellido")String apellido,
    @Param("mail")String mail,@Param("telefono")String telefono,@Param("alta")Boolean alta,@Param("id")Integer id);

    @Query("SELECT c FROM Cliente c WHERE c.alta= 1")
    List<Cliente> findAll();

    Optional<Cliente> findByDocumento(Long documento);
}
