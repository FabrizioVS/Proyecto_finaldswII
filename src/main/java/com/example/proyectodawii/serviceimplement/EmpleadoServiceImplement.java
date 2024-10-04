package com.example.proyectodawii.serviceimplement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.proyectodawii.model.Empleado;
import com.example.proyectodawii.model.Insumos;
import com.example.proyectodawii.model.Tipo;
import com.example.proyectodawii.repository.EmpleadoRepository;
import com.example.proyectodawii.service.EmpleadoService;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {
	@Autowired
	private EmpleadoRepository daoEmp;

	@Override
	public ResponseEntity<Map<String, Object>> listarEmpleados() {
		Map<String, Object> res = new HashMap<>();
		List<Empleado> emp = daoEmp.findAll();
		if (!emp.isEmpty()) {
			res.put("mensaje", "Lista de Empleados");
			res.put("empleados", emp);
			res.put("status", HttpStatus.OK);
			res.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.OK).body(res);
		} else {
			res.put("mensaje", "No existen registros");
			res.put("status", HttpStatus.NOT_FOUND);
			res.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}

	}

	@Override
	public ResponseEntity<Map<String, Object>> listarEmpleadosPorId(Long id) {
		Map<String, Object> res = new HashMap<>();
		Optional<Empleado> empExist = daoEmp.findById(id);

		if (empExist.isPresent()) {
			res.put("empleados", empExist);
			res.put("mensaje", "Busqueda Corrrecta");
			res.put("status", HttpStatus.OK);
			res.put("fecha", new Date());
			return ResponseEntity.ok().body(res);
		} else {
			res.put("mensaje", "Sin registros con ID: " + id);
			res.put("status", HttpStatus.NOT_FOUND);
			res.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> agregarEmpleado(Empleado empleado) {
		Map<String, Object> res = new HashMap<>();
		daoEmp.save(empleado);
		res.put("insumos", empleado);
		res.put("mensaje", "Registro Agregado");
		res.put("status", HttpStatus.CREATED);
		res.put("fecha", new Date());
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	@Override
	public ResponseEntity<Map<String, Object>> editarEmpleado(Empleado empleado, Long id) {

		Map<String, Object> res = new HashMap<>();
		Optional<Empleado> empExist = daoEmp.findById(id);
		if (empExist.isPresent()) {
			Empleado emp = empExist.get();
			emp.setNombre(empleado.getNombre());
			emp.setApellidoPaterno(empleado.getApellidoPaterno());
			emp.setApellidoMaterno(empleado.getApellidoMaterno());
			emp.setDescripcion(empleado.getDescripcion());
			emp.setEdad(empleado.getEdad());
			emp.setSexo(empleado.getSexo());
			emp.setTelefono(empleado.getTelefono());
			emp.setCorreo(empleado.getCorreo());
			emp.setDni(empleado.getCorreo());
			emp.setEstado(empleado.getEstado());
			Tipo nuevoTipo = new Tipo();
			nuevoTipo.setId(empleado.getTipo().getId());
			emp.setTipo(nuevoTipo);

			daoEmp.save(emp);
			res.put("insumo", emp);
			res.put("mensaje", "Datos del tipo de insumo modificado");
			res.put("status", HttpStatus.CREATED);
			res.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} else {
			res.put("mensaje", "Sin registros con ID: " + id);
			res.put("status", HttpStatus.NOT_FOUND);
			res.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarEmpleado(Long id) {

		Map<String, Object> res = new HashMap<>();
		Optional<Empleado> empExist = daoEmp.findById(id);
		if (empExist.isPresent()) {
			Empleado emp = empExist.get();
			daoEmp.delete(emp);
			res.put("mensaje", "Eliminado correctamente");
			res.put("status", HttpStatus.NO_CONTENT);
			res.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
		} else {
			res.put("mensaje", "Sin registros con ID: " + id);
			res.put("status", HttpStatus.NOT_FOUND);
			res.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarPorIdTipo(Long idTipo) {
		Map<String, Object> res = new HashMap<>();
		List<Empleado> emp = daoEmp.findByTipo_Id(idTipo);

		if (!emp.isEmpty()) {
			res.put("empleados", emp);
			res.put("mensaje", "Empleados encontrados");
			res.put("status", HttpStatus.OK);
			res.put("fecha", new Date());
			return ResponseEntity.ok(res);
		} else {
			res.put("mensaje", "No se encontraron empleados para el tipo ID: " + idTipo);
			res.put("status", HttpStatus.NOT_FOUND);
			res.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
	}

}
