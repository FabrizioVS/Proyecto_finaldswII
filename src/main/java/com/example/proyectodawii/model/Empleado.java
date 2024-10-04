package com.example.proyectodawii.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Data
@Entity
@Table (name = "empleados")
@AllArgsConstructor
@NoArgsConstructor

public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private long id;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String descripcion;

    private int edad;

    private String sexo;

    private String telefono;

    private String correo;

    private String dni;

    private String estado;

    @OneToOne
    @JoinColumn(name = "id_tipo_animal")
    private tipo_animal tipoAnimal;

}
