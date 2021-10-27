package com.egg.library.perisitence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="editoriales")
public final class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean alta;

    @JsonIgnore
    @OneToMany(mappedBy = "editorial",fetch = FetchType.EAGER)
    private List<Libro> libros;


}
