package com.example.proyectodawii.model;

import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.List;

@Data
@Entity
@Table(name = "animal")
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private long id;

    private String nombre;

    private String url;

    private int edad;

    private String estado;

    private int cantCuidadores;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipos;
}
