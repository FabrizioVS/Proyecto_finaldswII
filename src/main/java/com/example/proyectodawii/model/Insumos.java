package com.example.proyectodawii.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;
}
