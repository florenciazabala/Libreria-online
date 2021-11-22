package com.egg.library.perisitence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
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
@Table(name="clientes")
@EntityListeners(AuditingEntityListener.class)
public final class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Long documento;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String mail;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private Boolean alta;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Prestamo> prestamos;

    @OneToOne
    private Usuario usuario;

    @OneToOne(fetch = FetchType.EAGER)
    private Foto foto;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

}
