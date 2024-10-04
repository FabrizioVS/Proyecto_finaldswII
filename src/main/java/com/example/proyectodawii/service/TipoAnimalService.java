package com.example.proyectodawii.service;

import com.example.proyectodawii.model.tipo_animal;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TipoAnimalService {

    public ResponseEntity<Map<String, Object>> listarTipoAnimal();

    public ResponseEntity<Map<String, Object>> listarTipoanimalPorId(Long id);

    public ResponseEntity<Map<String, Object>> agregarTipoAnimal(tipo_animal tipoAnimal);

    public ResponseEntity<Map<String, Object>> editarTipoAnimal(tipo_animal tipoAnimal, Long id);

    public ResponseEntity<Map<String, Object>> eliminarTipoAnimal(Long id);

    public ResponseEntity<Map<String, Object>> eliminarTipoAnimalEstado(Long id);

    public ResponseEntity<Map<String, Object>> allTipoAnimalEstado();
}
