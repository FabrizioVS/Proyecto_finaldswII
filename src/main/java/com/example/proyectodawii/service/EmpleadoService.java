package com.example.proyectodawii.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.proyectodawii.model.Empleado;
import com.example.proyectodawii.model.Tipo;

public interface EmpleadoService {
	public ResponseEntity<Map<String, Object>> listarEmpleados();
	public ResponseEntity<Map<String, Object>> listarEmpleadosConEstadoA(String estado);

	public ResponseEntity<Map<String, Object>> listarEmpleadosPorId(Long id);

	public ResponseEntity<Map<String, Object>> agregarEmpleado(Empleado empleado);

	public ResponseEntity<Map<String, Object>> editarEmpleado(Empleado empleado, Long id);

	public ResponseEntity<Map<String, Object>> eliminarEmpleado(Long id);
	
	
	

	public ResponseEntity<Map<String, Object>> listarPorIdTipo(Long idTipo);
}
