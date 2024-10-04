package com.example.proyectodawii.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tipo")
@AllArgsConstructor
@NoArgsConstructor
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private long id;

    @Column(name = "descripcion")
    private String descripcion;

    private String estado;


}
