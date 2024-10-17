package com.example.proyectodawii.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyectodawii.model.Empleado;
import com.example.proyectodawii.service.EmpleadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/empleado/")
public class EmpleadoController {
	@Autowired
	private EmpleadoService serviceEmp;

	@GetMapping
	public ResponseEntity<Map<String, Object>> listarAll() {
		return serviceEmp.listarEmpleados();
	}
	
	@GetMapping("/estado/A")
	public ResponseEntity<Map<String, Object>> listarEstadoA(String estado) {
		return serviceEmp.listarEmpleadosConEstadoA(estado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listEmpPorId(@PathVariable Long id) {
		return serviceEmp.listarEmpleadosPorId(id);
	}

	@GetMapping("/tipo/{idTipo}")
	public ResponseEntity<Map<String, Object>> listarEmpleadosPorIdTipo(@PathVariable Long idTipo) {
		return serviceEmp.listarPorIdTipo(idTipo);
	}

	@PostMapping
	ResponseEntity<Map<String, Object>> AgregarEmpleado(@RequestBody Empleado empleado) {
		return serviceEmp.agregarEmpleado(empleado);
	}

	@PutMapping("/{id}")
	ResponseEntity<Map<String, Object>> editarEmpleado(@RequestBody Empleado empleado, @PathVariable Long id) {
		return serviceEmp.editarEmpleado(empleado, id);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<Map<String, Object>> eliminarEmpleado(@PathVariable Long id) {
		return serviceEmp.eliminarEmpleado(id);
	}

}
