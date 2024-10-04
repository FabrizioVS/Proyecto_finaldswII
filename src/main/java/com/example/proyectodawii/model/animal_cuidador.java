package com.example.proyectodawii.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

@Data
@Entity
@Table(name = "animal_cuidador")
@AllArgsConstructor
@NoArgsConstructor
public class animal_cuidador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ani_cuidador")
    private long id;


    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;

}
