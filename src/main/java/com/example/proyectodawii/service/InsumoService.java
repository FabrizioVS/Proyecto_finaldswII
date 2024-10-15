package com.example.proyectodawii.service;

import com.example.proyectodawii.model.Insumos;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface InsumoService {

    public ResponseEntity<Map<String, Object>> listarInusmo();

    public ResponseEntity<Map<String, Object>> listarInusmoPorId(Long id);

    public ResponseEntity<Map<String, Object>> agregarInusmo(Insumos insumos);

    public ResponseEntity<Map<String, Object>> editarInusmo(Insumos insumos, Long id);

    public ResponseEntity<Map<String, Object>> eliminarInusmo(Long id);

    public ResponseEntity<Map<String, Object>> eliminarInusmoEstado(Long id);

    public ResponseEntity<Map<String, Object>> listarPorIdTipo(Long idTipo);

    public ResponseEntity<Map<String, Object>> allInusmoEstado();


}
