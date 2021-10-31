package com.egg.library.perisitence.DAO;

import com.egg.library.perisitence.entity.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FotoDAO extends JpaRepository<Foto,Integer> {

    @Modifying
    @Query("UPDATE Foto f SET f.nombre = :nombre, f.mime = :mime, f.ruta = :ruta, f.alta = :alta WHERE f.id = :id")
    void update(@Param("nombre") String nombre,@Param("mime") String mime,@Param("ruta") String ruta,@Param("alta") Boolean alta
    ,@Param("id")Integer id);

    Optional<Foto> findByRuta(String ruta);
}
