package com.egg.library.perisitence.entity;

import com.egg.library.util.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="libros")
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;
}
