package com.example.proyectodawii.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "insumos")
@AllArgsConstructor
@NoArgsConstructor
public class Insumos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumos")
    private long id;

    private String nombre;

    private String estado;

    @OneToOne
    @JoinColumn(name = "id_tipo_animal")
    private tipo_animal tipoAnimal;
}
