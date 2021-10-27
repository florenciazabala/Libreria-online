package com.egg.library.perisitence.DAO;

import com.egg.library.perisitence.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EditorialDAO extends JpaRepository<Editorial,Integer> {

    @Modifying
    @Query("UPDATE Editorial e SET e.nombre = :nombre, e.alta= :alta WHERE e.id= :id")
    public void update(@Param("nombre") String nombre, @Param("alta") Boolean alta, @Param("id") Integer id);

    @Query("SELECT e FROM Editorial e WHERE e.alta=1")
    List<Editorial> findAll();

    @Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
    Editorial findByName(@Param("nombre") String nombre);
}
