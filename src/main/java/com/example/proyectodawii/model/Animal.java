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

    @ManyToOne
    @JoinColumn(name = "id_tipo_animal")
    private tipo_animal tipo_animal;

    @OneToOne
    @JoinColumn(name = "id_ani_cuidador")
    private animal_cuidador animal_cuidador;
}
