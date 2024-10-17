package com.example.proyectodawii.service;

import com.example.proyectodawii.model.Tipo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TipoService {

    public ResponseEntity<Map<String, Object>> listarTipoAnimal();
    public ResponseEntity<Map<String, Object>> listarTipoAnimalPorEstado(String estado);

    public ResponseEntity<Map<String, Object>> listarTipoanimalPorId(Long id);

    public ResponseEntity<Map<String, Object>> agregarTipoAnimal(Tipo tipoAnimal);

    public ResponseEntity<Map<String, Object>> editarTipoAnimal(Tipo tipoAnimal, Long id);

    public ResponseEntity<Map<String, Object>> eliminarTipoAnimal(Long id);

    public ResponseEntity<Map<String, Object>> eliminarTipoAnimalEstado(Long id);
}
