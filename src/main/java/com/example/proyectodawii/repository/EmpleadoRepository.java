package com.example.proyectodawii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyectodawii.model.Empleado;
import com.example.proyectodawii.model.Tipo;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	 List<Empleado> findByTipo_Id(Long tipoId);

}
