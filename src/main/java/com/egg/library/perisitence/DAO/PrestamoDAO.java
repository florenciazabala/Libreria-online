package com.egg.library.perisitence.DAO;

import com.egg.library.perisitence.entity.Cliente;
import com.egg.library.perisitence.entity.Libro;
import com.egg.library.perisitence.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrestamoDAO extends JpaRepository<Prestamo,Integer> {

    @Modifying
    @Query("UPDATE Prestamo p SET p.fechaPrestamo = :fechaPrestamo, p.fechaDevolucion = :fechaDevolucion, " +
            "p.alta = :alta,p.libro = :libro, p.cliente = :cliente" +
            " WHERE p.id = :id")
    void update(@Param("fechaPrestamo")LocalDate fechaPrestamo,@Param("fechaDevolucion")LocalDate fechaDevolucion,
                @Param("alta")Boolean alta,@Param("libro")Libro libro,@Param("cliente")Cliente cliente,
                @Param("id")Integer id);

    @Query("SELECT p FROM Prestamo p WHERE p.alta =1")
    List<Prestamo> findAll();

    @Query(value = "SELECT * FROM prestamos p WHERE p.libro_isbn = :libro AND p.alta =1",nativeQuery = true)
    List<Prestamo> findByLibro(@Param("libro") Long isbnLibro);

    @Query(value = "SELECT * FROM prestamos p WHERE p.cliente_id = :cliente AND p.alta =1",nativeQuery = true)
    List<Prestamo> findByCliente(@Param("cliente") Integer isCustomer);

    //Uso una query nativa para no tener que mapear los objetos libro y cliente y facilitar el desacoplamiento
    @Query(value = "SELECT * FROM prestamos p WHERE p.libro_isbn = :libro AND p.cliente_id = :cliente AND p.alta =1",nativeQuery = true)
    Optional<Prestamo> findByLibroAndCliente(@Param("libro") Long isbnLibro, @Param("cliente") Integer idCliente);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaPrestamo = :fechaPrestamo AND p.alta =1")
    List<Prestamo> findByFechaPrestamo(@Param("fechaPrestamo") LocalDate fechaPrestamo);

    @Query("SELECT p FROM Prestamo p WHERE p.alta =1 AND p.fechaDevolucion BETWEEN :desdeFechaDevolucion AND :hastaFechaDevolucion ")
    List<Prestamo> findByFechaDevolucion(@Param("desdeFechaDevolucion") LocalDate desdeFechaDevolucion,
                                         @Param("hastaFechaDevolucion")LocalDate hastaFechaDevolucion);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaDevolucion < :fechaDevolucion AND p.alta =1 ORDER BY p.fechaDevolucion DESC")
    List<Prestamo> findByFechaDevolucionVencida(@Param("fechaDevolucion") LocalDate fechaDevolucion);
}
