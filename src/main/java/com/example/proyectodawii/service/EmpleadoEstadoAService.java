package com.example.proyectodawii.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.proyectodawii.model.Empleado;

public interface EmpleadoEstadoAService {
	public ResponseEntity<Map<String, Object>> listarEmpleadosConEstadoA(String estado);

	public ResponseEntity<Map<String, Object>> listarEmpleadosPorIdConEstadoA(Long id);

	public ResponseEntity<Map<String, Object>> agregarEmpleadoConEstadoA(Empleado empleado);

	public ResponseEntity<Map<String, Object>> editarEmpleadoConEstadoA(Empleado empleado, Long id);

	public ResponseEntity<Map<String, Object>> setearEstadoE(Long id);
}
