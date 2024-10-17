package com.example.proyectodawii.repository;

import com.example.proyectodawii.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long>{
	Optional<Tipo> findByIdAndEstado(Long idTipo, String estado);
	   List<Tipo> findAllByEstado(String estado);
}
