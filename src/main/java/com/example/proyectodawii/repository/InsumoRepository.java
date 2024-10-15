package com.example.proyectodawii.repository;

import com.example.proyectodawii.model.Animal;
import com.example.proyectodawii.model.Insumos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumos, Long> {

    List<Insumos> findByTipo_Id(Long tipoId);

    List<Insumos> findAllByEstado(String estado);

}
