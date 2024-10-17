package com.example.proyectodawii.serviceimplement;

import com.example.proyectodawii.model.Insumos;
import com.example.proyectodawii.model.Tipo;
import com.example.proyectodawii.repository.TipoRepository;
import com.example.proyectodawii.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipoServiceImplement implements TipoService {

    @Autowired
    private TipoRepository dao;


    @Override
    public ResponseEntity<Map<String, Object>> listarTipoAnimal() {
        Map<String,Object> respuesta = new HashMap<>();
        List<Tipo> tipo_animales = dao.findAll();

        if(!tipo_animales.isEmpty())
        {
            respuesta.put("mensaje","Lista de Tipos de Animales");
            respuesta.put("tipo", tipo_animales);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarTipoanimalPorId(Long id) {
        Map<String,Object> respuesta = new HashMap<>();
        Optional<Tipo> tipo_animal = dao.findById(id);

        if(tipo_animal.isPresent())
        {
            respuesta.put("tipo",tipo_animal);
            respuesta.put("mensaje","Busqueda Corrrecta");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.ok().body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarTipoAnimal(Tipo tipoAnimal) {
        Map<String,Object> respuesta =  new HashMap<>();
        dao.save(tipoAnimal);
        respuesta.put("tipoAnimal", tipoAnimal);
        respuesta.put("mensaje", "Se añadio correctamente el tipo de animal");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarTipoAnimal(Tipo tipoA, Long id) {
        Map<String,Object> respuesta =  new HashMap<>();
        Optional<Tipo> tipoAnimalExiste = dao.findById(id);
        if (tipoAnimalExiste.isPresent())
        {
            Tipo tipoAnimal = tipoAnimalExiste.get();
            tipoAnimal.setDescripcion(tipoA.getDescripcion());
            tipoAnimal.setEstado(tipoA.getEstado());
            dao.save(tipoAnimal);
            respuesta.put("tipoAnimal", tipoAnimal);
            respuesta.put("mensaje", "Datos del tipo de animal modificado");
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarTipoAnimal(Long id) {
        Map<String,Object> respuesta =  new HashMap<>();
        Optional<Tipo> tipoAnimalExiste = dao.findById(id);
        if(tipoAnimalExiste.isPresent())
        {
            Tipo tipoAnimal = tipoAnimalExiste.get();
            dao.delete(tipoAnimal);
            respuesta.put("mensaje", "Eliminado correctamente");
            respuesta.put("status", HttpStatus.NO_CONTENT);
            respuesta.put("fecha", new Date());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarTipoAnimalEstado(Long id) {
        Map<String,Object> respuesta = new HashMap<>();
        Optional<Tipo> tipoAnimalExiste = dao.findById(id);
        if(tipoAnimalExiste.isPresent())
        {
            Tipo tipoAnimal = tipoAnimalExiste.get();
            tipoAnimal.setEstado("E");
            dao.save(tipoAnimal);
            respuesta.put("mensaje", "Se eliminado correctamente");
            respuesta.put("status", HttpStatus.NO_CONTENT);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

	@Override
	public ResponseEntity<Map<String, Object>> listarTipoAnimalPorEstado(String estado) {
		
	      Map<String,Object> respuesta =  new HashMap<>();
	        List<Tipo> tipos = dao.findAllByEstado(estado);
	        if(!tipos.isEmpty())
	        {
	            respuesta.put("mensaje", "Lista de insumos");
	            respuesta.put("tipo", tipos);
	            respuesta.put("status", HttpStatus.OK);
	            respuesta.put("fecha", new Date());
	            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	        }
	        else
	        {
	            respuesta.put("mensaje", "No existen registros");
	            respuesta.put("status", HttpStatus.NOT_FOUND);
	            respuesta.put("fecha", new Date());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	        }
	}


}
