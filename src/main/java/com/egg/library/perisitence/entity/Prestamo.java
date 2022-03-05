package com.egg.library.perisitence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name="prestamos")
@EntityListeners(AuditingEntityListener.class)
public final class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;
}
