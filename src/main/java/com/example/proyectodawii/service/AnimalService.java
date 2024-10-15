package com.example.proyectodawii.service;

import com.example.proyectodawii.model.Animal;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AnimalService {

    public ResponseEntity<Map<String, Object>> ListarAnimales();

    public ResponseEntity<Map<String, Object>> listarAnimalesPorId(Long id);

    public ResponseEntity<Map<String, Object>> agregarAnimales(Animal animal);

    public ResponseEntity<Map<String, Object>> editarAnimales(Animal animal, Long id);

    public ResponseEntity<Map<String, Object>> eliminarAnimales(Long id);

    public ResponseEntity<Map<String, Object>> eliminarAnimalEstado(Long id);

    public ResponseEntity<Map<String, Object>> listarPorIdTipo(Long idTipo);

    public ResponseEntity<Map<String, Object>> allAnimalEstado();


}
