package com.egg.library.perisitence.DAO;

import com.egg.library.perisitence.entity.Autor;
import com.egg.library.perisitence.entity.Editorial;
import com.egg.library.perisitence.entity.Libro;
import com.egg.library.util.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface LibroDAO extends JpaRepository<Libro,Long> {

    @Modifying
    @Query("UPDATE Libro l SET l.titulo = :titulo, l.anio = :anio, l.ejemplares = :ejemplares, " +
            "l.ejemplaresPrestados= :ejemplaresPrestados, l.ejemplaresRestantes = :ejemplaresRestantes, l.alta = :alta," +
            "l.genero= :genero, l.autor= :autor, l.editorial= :editorial  WHERE l.isbn = :isbn")
    void update(@Param("titulo") String titulo, @Param("anio") Integer anio, @Param("ejemplares") Integer ejemplares,
    @Param("ejemplaresPrestados") Integer ejemplaresPrestados, @Param("ejemplaresRestantes") Integer ejemplaresRestantes,
                       @Param("alta") Boolean alta, @Param("genero")Genre genero,
                @Param("autor")Autor autor,@Param("editorial")Editorial editorial,@Param("isbn") Long isbn);



    @Query("SELECT l FROM Libro l WHERE l.alta=1")
    List<Libro> findAll();

    @Query("SELECT l FROM Libro l WHERE CONCAT_WS(l.isbn,l.titulo,l.anio,l.genero,l.autor.nombre,l.editorial.nombre) LIKE %:search%")
    List<Libro> findByAllFields(@Param("search") String search);

    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:titulo% AND l.alta=1")
    List<Libro> findByTitulo(@Param("titulo") String titulo);

    @Query("SELECT l FROM Libro l WHERE l.genero = :genero AND l.alta=1")
    List<Libro> findByGenero(@Param("genero") Genre genero);

    @Query("SELECT l FROM Libro l WHERE l.autor.id= :autorId")
    List<Libro> findByAutor(@Param("autorId") Integer autorId);

    @Query("SELECT l FROM Libro l WHERE l.editorial.id= :editorialId")
    List<Libro> findByEditorial(@Param("editorialId") Integer editorialId);

    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo AND l.autor.id= :autorId")
    Optional<Libro> findByTitleAndAuthor(@Param("titulo")String titulo, @Param("autorId") Integer autorId);

    @Query("SELECT l FROM Libro l WHERE l.alta=0")
    List<Libro> findDismissBooks();

    @Query("SELECT l FROM Libro l WHERE l.ejemplaresRestantes>0")
    List<Libro> findAvaiblesBooks();

    @Query(value = "SELECT (SELECT COUNT(l.isbn)  FROM libros l WHERE l.genero= :genero) / " +
            "T.total *100 FROM libros l " +
            "CROSS JOIN (SELECT COUNT(l.isbn) as total  FROM libros l) T " +
            "WHERE l.genero= :genero GROUP BY l.genero", nativeQuery = true)
    BigDecimal getPorcentajeGenero(@Param("genero") int genero);
}
