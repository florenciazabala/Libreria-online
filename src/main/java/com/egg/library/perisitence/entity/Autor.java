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

@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@Entity
@Table(name="autores")
public final class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean alta;

    @JsonIgnore
    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY)
    private List<Libro> libros;

    @OneToOne(fetch = FetchType.EAGER)
    private Foto foto;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;
}
