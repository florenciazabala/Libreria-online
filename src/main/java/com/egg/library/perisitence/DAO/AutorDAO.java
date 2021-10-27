package com.egg.library.perisitence.DAO;

import com.egg.library.perisitence.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AutorDAO extends JpaRepository<Autor,Integer> {

    @Modifying
    @Query("UPDATE Autor a SET a.nombre = :nombre, a.alta= :alta WHERE a.id= :id")
    public void update(@Param("nombre") String nombre, @Param("alta") Boolean alta, @Param("id") Integer id);

    @Query("SELECT a FROM Autor a WHERE a.alta=1")
    List<Autor> findAll();

    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) = LOWER(:nombre)")
    Autor findByName(@Param("nombre") String nombre);
}
