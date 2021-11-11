package com.egg.library.perisitence.DAO;

import com.egg.library.perisitence.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface RolDAO extends JpaRepository<Rol,Integer> {

    Optional<Rol> findByRol(String rol);


}
