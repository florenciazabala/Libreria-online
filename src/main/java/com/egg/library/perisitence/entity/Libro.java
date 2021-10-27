package com.egg.library.perisitence.entity;

import com.egg.library.util.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="libros")
public final class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private Genre genero;
    @Column(nullable = false)
    private Integer anio;
    @Column(nullable = false)
    private Integer ejemplares;
    @Column(nullable = false)
    private Integer ejemplaresPrestados;
    @Column(nullable = false)
    private Integer ejemplaresRestantes;
    @Column(nullable = true)
    private Boolean alta;

    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    @ManyToOne
    private Autor autor;

    @JoinColumn(name = "editorial_id", referencedColumnName = "id")
    @ManyToOne
    private Editorial editorial;

    @JsonIgnore
    @OneToMany(mappedBy = "libro")
    private List<Prestamo> prestamos;

}
