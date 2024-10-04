package com.example.proyectodawii.repository;

import com.example.proyectodawii.model.tipo_animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Tipo_AnimalRepository extends JpaRepository<tipo_animal, Long>{

    List<tipo_animal> findAllByEstado(String estado);
}
