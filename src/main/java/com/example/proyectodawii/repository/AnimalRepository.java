package com.example.proyectodawii.repository;

import com.example.proyectodawii.model.Animal;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByTipo_Id(Long tipoId);

    List<Animal> findAllByEstado(String estado);
}
