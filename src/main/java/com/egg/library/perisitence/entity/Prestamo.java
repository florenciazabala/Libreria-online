package com.egg.library.perisitence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name="prestamos")
public final class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private LocalDate fechaPrestamo;
    @Column(nullable = false)
    private LocalDate fechaDevolucion;
    @Column(nullable = false)
    private Boolean alta;

    @JoinColumn(name="libro_isbn", referencedColumnName = "isbn")
    @ManyToOne
    private Libro libro;
    @JoinColumn(name="cliente_id", referencedColumnName = "id")
    @ManyToOne
    private Cliente cliente;
}
